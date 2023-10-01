package com.example.microservicepfe.web;


import com.example.microservicepfe.dao.*;
import com.example.microservicepfe.models.*;
import com.example.microservicepfe.payload.request.EmailRequest;
import com.example.microservicepfe.payload.request.SignupRequest;
import com.example.microservicepfe.payload.response.MessageResponse;
import com.example.microservicepfe.security.services.ClientService;
import com.example.microservicepfe.security.services.EmailService;
import com.example.microservicepfe.services.DevisService;
import com.example.microservicepfe.services.SinistreService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Gestionnaire")
public class GestionnaireController {
    private final ClientService clientService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private SinistreService sinistreService;

    @Autowired
    private DevisService devisService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    EmailService emailService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private GestionnaireRepository gestionnaireRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FcmTokenRepository fcmTokenRepository;
    @Autowired
    public GestionnaireController (ClientService clientService) {
        this.clientService = clientService;
    }

   /* @PostMapping("/addClient")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }*/

    @PostMapping("/addClient")
    public ResponseEntity<?> createClient(@Validated @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String activationCode = UUID.randomUUID().toString();
        // Create new client's account
        Client client = new Client(
                activationCode,
                true, // Set the initial 'enabled' value to false
                false, // Set the initial 'authenticated' value to false
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getAdresse(),
                signUpRequest.getTypeIDNT(),
                signUpRequest.getTypePers(),
                signUpRequest.getNumTel(),
                encoder.encode(signUpRequest.getPassword())
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(clientRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "gestionnaire":
                        Role gestionnaireRole = roleRepository.findByName(ERole.ROLE_GESTIONNAIRE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(gestionnaireRole);
                        break;
                    default:
                        Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(clientRole);
                }
            });
        }

        client.setRoles(roles);
        client.setActivationCode(activationCode);
        userRepository.save(client);
        // Envoyez un e-mail d'activation au nouveau client
        // String activationLink = "http://localhost:8060/activate/" + activationCode;
        // Envoyer un code d'activation
        String activationLink = activationCode;

        emailService.sendActivationEmail(client.getEmail(), activationLink);

        return ResponseEntity.ok("Un e-mail d'activation a été envoyé à l'adresse e-mail du client.");
    }


    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("updateClient/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        // Vérifiez si le client avec l'ID donné existe
        if (!clientService.getClientById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        client.setId(id);
        Client updatedClient = clientService.updateClient(client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("deleteClient/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        // Vérifiez si le client avec l'ID donné existe
        if (!clientService.getClientById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clientService.deleteClientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sinistres")
    public ResponseEntity<List<Sinistre>> getAllSinistres() {
        List<Sinistre> sinistres = sinistreService.getAllSinistres();
        return new ResponseEntity<>(sinistres, HttpStatus.OK);
    }
    @CrossOrigin(origins = "*")

    @GetMapping("sinistre/{id}")
    public ResponseEntity<Sinistre> getSinistreById(@PathVariable Long id) {
        return sinistreService.getSinistreById(id)
                .map(sinistre -> new ResponseEntity<>(sinistre, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("deleteSinistre/{id}")
    public ResponseEntity<Void> deleteSinistre(@PathVariable Long id) {
        if (!sinistreService.getSinistreById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        sinistreService.deleteSinistreById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("sinistre/{id}/repondre")
    public void repondreSinistre(@PathVariable Long id, @RequestBody String message) throws Exception {
        Sinistre sinsitre = sinistreService.getSinistreById(id)
                .orElseThrow(() -> new Exception("sinsitre : " + id));

        // Récupérer l'utilisateur associé au Sinistre
        User user = sinsitre.getUser();

        // Créer une nouvelle instance de notification avec l'utilisateur et le contenu de la réponse
        Notification notification = new Notification();
        notification.setContenu(message);
        notification.setTitle("Sinistre est approuvé");
        notification.setUser(user);

        // Enregistrer la notification dans la base de données
        notificationRepository.save(notification);

        String userExpoPushToken = getExpoPushToken(user);
        System.out.println(userExpoPushToken);

        // Envoyer la notification
        try {
            String response = sendExpoPushNotification(userExpoPushToken, message);
            System.out.println("Notification envoyée avec succès : " + response);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'envoi de la notification : " + e.getMessage());
            // Gérer l'erreur si la notification ne peut pas être envoyée
        }
    }

    // Méthode pour récupérer le token Expo push de l'utilisateur depuis la base de données
    private String getExpoPushToken(User user) {
        Long userId = user.getId();

        // Recherche du token Expo push dans la table fcmtoken en utilisant l'ID de l'utilisateur
        FcmToken fcmToken = fcmTokenRepository.findByUserId(userId);

        if (fcmToken != null) {
            return fcmToken.getToken();
        } else {
            // Si le token n'est pas trouvé, retournez null ou une valeur par défaut
            return null;
        }
    }

    // Méthode pour envoyer la notification push via l'API d'Expo
    private String sendExpoPushNotification(String expoPushToken, String contenu) throws IOException {
        String expoPushApiUrl = "https://exp.host/--/api/v2/push/send";

        // Create the notification message
        JSONObject notificationData = new JSONObject();
        notificationData.put("to", expoPushToken);

        // Create an object for the notification body
        //JSONObject bodyData = new JSONObject();
        notificationData.put("title", "Sinistre approuvé");
        notificationData.put("body", contenu);

        // Set the style for the title to be bold
        JSONObject titleStyle = new JSONObject();
        titleStyle.put("fontFamily", "Montserrat-Bold"); // Assuming "Montserrat-Bold" is the name of the bold font

        // Add the title style to the body object
        //bodyData.put("titleStyle", titleStyle);

        // Set the body object as the notification body
        //notificationData.put("body", bodyData);

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set up the request entity
        HttpEntity<String> entity = new HttpEntity<>(notificationData.toString(), headers);

        // Send the push notification using Expo's API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(expoPushApiUrl, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }
    @GetMapping("/devis")
    public ResponseEntity<List<Devis>> getAllDevis() {
        List<Devis> devis = devisService.getAllDevis();
        return new ResponseEntity<>(devis, HttpStatus.OK);
    }

    @GetMapping("devis/{id}")
    public ResponseEntity<Devis> getDevisById(@PathVariable Long id) {
        return devisService.getDevisById(id)
                .map(devis -> new ResponseEntity<>(devis, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

   /* @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailRequest.getEmail());
            helper.setSubject(emailRequest.getSubject());

            // Customize the email content based on packType and typeOccupantNom
            //String emailContent = "<html><body style='font-family: Arial, sans-serif; font-size: 16px; color: #333;'>"
                    //+ "<p><strong>Vous avez choisi le pack: " + emailRequest.getPackType() + " pour un type Occupant: " + emailRequest.getTypeOccupantNom() + " pour le produit Multirisque Habitation.</strong><br />"
                    //+ "<span style='font-size: 18px; color: #007bff;'>Le montant total en TTC est : " + emailRequest.getContent() + "</span></p></body></html>";

            String emailContent =  "<!DOCTYPE html><html><head><title>Votre devis Multirisque Habitation</title></head><body style='font-family: Arial, sans-serif; font-size: 16px;'>"
                    + "<p style='color: #ed3026;'><strong>Bonjour " + emailRequest.getName() + ",</strong></p>"
                    + "<p>Nous vous remercions d'avoir choisi notre produit Multirisque Habitation. Voici un résumé de votre devis :</p>"
                    + "<strong style='color: #204393;'>Pack choisi :</strong> " +  emailRequest.getPackType() + "<br />"
                    + "<strong style='color: #204393;'>Type d'occupant :</strong> " + emailRequest.getTypeOccupantNom() + "<br />"
                    + "<strong style='color: #204393;'>Montant total en TTC :</strong> " +  emailRequest.getContent() + " dt <br />"
                    + "<p>Si vous avez des questions ou avez besoin d'aide supplémentaire, n'hésitez pas à nous contacter.</p>"
                    + "<p style='color: #ed3026;'>Cordialement,<br />AMI Assurances </p>"
                    + "</body></html>";
            helper.setText(emailContent, true); // Set the second parameter to true to indicate HTML content

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception
        }
    }*/
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailRequest.getEmail());
            helper.setSubject(emailRequest.getSubject());

            // Styles CSS personnalisés
            String styles = "<style>" +
                    "body {" +
                    "    font-family: Arial, sans-serif;" +
                    "    color: #000;" +
                    "    margin: 0;" +
                    "    padding: 0;" +
                    "}" +
                    ".container {" +
                    "    border: 1px solid #ccc;" +
                    "    width: 100%; /* Largeur à 100% pour le rendu mobile */" +
                    "    margin: 0 auto;" +
                    "    padding: 20px;" +
                    "    background-color: #1c4191;" +
                    "    box-sizing: border-box;" +
                    "}" +
                    ".logo {" +
                    "    float: left;" +
                    "    margin-right: 20px;" +
                    "}" +
                    ".header {" +
                    "    text-align: center;" +
                    "    margin-bottom: 20px;" +
                    "    background-color: #1c4191;" + // Couleur bleue
                    "}" +
                    ".header h1 {" +
                    "    font-size: 26px;" +
                    "    font-weight: bold;" +
                    "    color: #fff; /* Texte en blanc */" +
                    "}" +
                    ".devis-details {" +
                    "    margin-top: 20px;" +
                    "    padding: 10px;" +
                    "    background-color: #fff;" +
                    "}" +
                    ".devis-details p {" +
                    "    margin: 10px 0;" +
                    "    font-size: 24px;" +
                    "}" +
                    ".footer {" +
                    "    margin-top: 20px;" +
                    "background-color: #1c4191;" +
                    "    padding: 10px;" +
                    "}" +
                    ".footer h2 {" +
                    "    font-size: 24px;" +
                    "    margin-top: 0;" +
                    "    color: #fff; /* Texte en blanc */" +
                    "}" +
                    "@media (max-width: 600px) {" + // Styles pour les écrans de moins de 600px de large (mobile)
                    "    .container {" +
                    "        width: 100%;" +
                    "        padding: 10px;" +
                    "    background-color: #1c4191;" + // Couleur bleue
                    "    }" +
                    "    .header h1 {" +
                    "        font-size: 20px;" + // Taille de police réduite
                    "    }" +
                    "    .devis-details p {" +
                    "        font-size: 18px;" + // Taille de police réduite
                    "    }" +
                    "    .footer h2 {" +
                    "        font-size: 20px;" + // Taille de police réduite
                    "    }" +
                    "}" +
                    "</style>";

            // Personnalisez le contenu de l'e-mail ici
            String emailContent = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Devis</title>\n" +
                    styles + // Intégration des styles CSS personnalisés
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"header\">\n" +
                    "        <img src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTlhwWgspMCTnG1MALMAh6x0hTECo52RooV17nrSMXJrQ&s\"  width=\"200\" />\n" +
                    "    </div>\n" +
                    "    <div class=\"devis-details\">\n" +
                    "        <p><strong style='color:  #ed3026;'>Bonjour " + emailRequest.getName() + ",</strong></p>\n" + // Couleur rouge
                    "        <p>Nous vous remercions d'avoir choisi notre produit Multirisque Habitation. Voici un résumé de votre devis :</p>\n" +
                    "        <p><strong style='color:  #ed3026;'>Pack choisi :</strong> " + emailRequest.getPackType() + "</p>\n" + // Couleur rouge
                    "        <p><strong style='color:  #ed3026;'>Type d'occupant :</strong> " + emailRequest.getTypeOccupantNom() + "</p>\n" + // Couleur rouge
                    "        <p><strong>Montant total en TTC :</strong> " + emailRequest.getContent() + " dt </p>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        <p style='color: #fff;'>Si vous avez des questions ou avez besoin d'aide supplémentaire, n'hésitez pas à nous contacter.</h2>\n" +
                    "        <p style='color: #fff;'> Cordialement,<br />AMI Assurances </p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

            helper.setText(emailContent, true); // Définir le deuxième paramètre sur true pour indiquer le contenu HTML

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'exception
        }
    }




/*    @PutMapping("/updateMyProfile")
    public ResponseEntity<Gestionnaire> updateMyProfile(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Gestionnaire updatedGestionnaire) {
        // Get the authenticated gestionnaire's username from the UserDetails
        String username = userDetails.getUsername();

        // Find the gestionnaire by username
        Optional<Gestionnaire> optionalGestionnaire = gestionnaireRepository.findByUsername(username);
        if (!optionalGestionnaire.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Gestionnaire exists, proceed with profile update
        Gestionnaire gestionnaireToUpdate = optionalGestionnaire.get();
        gestionnaireToUpdate.setName(updatedGestionnaire.getName());
        gestionnaireToUpdate.setEmail(updatedGestionnaire.getEmail());
        gestionnaireToUpdate.setNumTel(updatedGestionnaire.getNumTel());

        // Save the updated gestionnaire in the database
        Gestionnaire updatedProfile = gestionnaireRepository.save(gestionnaireToUpdate);

        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }*/

/*
    @GetMapping("/profile")
    public ResponseEntity<Optional<Gestionnaire>> profile(Authentication authentication) {
        // Vérifier que l'utilisateur est authentifié
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Récupérer l'utilisateur connecté
        String username = authentication.getName();
        Optional<Gestionnaire> gestionnaire = gestionnaireRepository.findByUsername(username);
        if (gestionnaire == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(gestionnaire);
    }
*/

    @GetMapping("/profile")
    public ResponseEntity<Optional<User>> getProfile(Authentication authentication) {
        // Vérifier que l'utilisateur est authentifié
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        Optional<User> user =userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(user);
    }
    @PutMapping("/updateMyProfile")
    public ResponseEntity<User> updateMyProfile(@AuthenticationPrincipal UserDetails userDetails, @RequestBody User updatedProfile) {
        // Get the authenticated user's username from the UserDetails
        String username = userDetails.getUsername();

        // Find the user by username
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // User exists, proceed with profile update
        User userToUpdate = optionalUser.get();

        // Update only the properties that are present in the updatedProfile object
        if (updatedProfile.getName() != null) {
            userToUpdate.setName(updatedProfile.getName());
        }

        if (updatedProfile.getEmail() != null) {
            userToUpdate.setEmail(updatedProfile.getEmail());
        }

        if (updatedProfile.getNumTel() != null) {
            userToUpdate.setNumTel(updatedProfile.getNumTel());
        }

        // Save the updated user in the database
        User updatedUser = userRepository.save(userToUpdate);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String currentPassword, @RequestParam String newPassword, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
       User user = userRepository.findUserByUsername(userDetails.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Check if the provided current password matches the user's actual password
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid current password");
        }

        // Hash the new password
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        userRepository.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }
}

