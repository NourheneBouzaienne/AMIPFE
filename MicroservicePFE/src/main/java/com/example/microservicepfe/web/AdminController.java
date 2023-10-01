package com.example.microservicepfe.web;

import com.example.microservicepfe.dao.GestionnaireRepository;
import com.example.microservicepfe.dao.NotificationRepository;
import com.example.microservicepfe.dao.RoleRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.*;
import com.example.microservicepfe.payload.request.EmailRequest;
import com.example.microservicepfe.payload.request.SignupRequest;
import com.example.microservicepfe.payload.response.MessageResponse;
import com.example.microservicepfe.security.services.ClientService;
import com.example.microservicepfe.security.services.EmailService;
import com.example.microservicepfe.services.DevisService;
import com.example.microservicepfe.services.GestionnaireService;
import com.example.microservicepfe.services.SinistreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.*;

import static com.example.microservicepfe.utils.PasswordGenerator.generateRandomPassword;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Admin")
public class AdminController {

    private final ClientService clientService;
    @Autowired
    UserRepository userRepository;


    @Autowired
    GestionnaireRepository gestionnaireRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private SinistreService sinistreService;

    @Autowired
    private GestionnaireService gestionnaireService;

    @Autowired
    private DevisService devisService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    EmailService emailService;

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public AdminController (ClientService clientService) {
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

    @PostMapping("/addGestionnaire")
    public ResponseEntity<?> createGestionnaire(@Validated @RequestBody SignupRequest signUpRequest) {
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

        // Create new account
       Gestionnaire gestionnaire = new Gestionnaire(
                activationCode,
                true, // Set the initial 'enabled' value to false
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getNumTel(),
                encoder.encode(signUpRequest.getPassword())
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role gestionnaireRole = roleRepository.findByName(ERole.ROLE_GESTIONNAIRE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(gestionnaireRole);
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
                        gestionnaireRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(gestionnaireRole);
                }
            });
        }

        gestionnaire.setRoles(roles);
        gestionnaire.setActivationCode(activationCode);
        String generatedPassword = generateRandomPassword() ;
        gestionnaire.setPassword(encoder.encode(generatedPassword));

        userRepository.save(gestionnaire);


        String activationEmailContent = "<!DOCTYPE html><html><head><title>Activation de compte</title></head><body><p>Bienvenue, "
                + gestionnaire.getName() + " !</p><p>Votre compte a été créé avec succès en tant que gestionnaire.</p>"
                + "<p>Voici vos informations de connexion :</p><p><strong>Identifiant :</strong> "
                + gestionnaire.getUsername() + "</p><p><strong>Mot de passe :</strong> " + generatedPassword + "</p>"
                + "<p>Cliquez sur le lien ci-dessous pour activer votre compte et accéder à la page de connexion :</p>"
                + "<a href=http://localhost:3000/#/login/signinGest target=\"_blank\">Activer mon compte et me connecter</a></body></html>";

        // Envoyer l'e-mail d'activation
        emailService.sendCompteEmail(gestionnaire.getEmail(), activationEmailContent);

        return ResponseEntity.ok("Un e-mail d'activation a été envoyé à l'adresse e-mail du gestionnaire.");
    }

    @GetMapping("/gestionnaires")
    public ResponseEntity<List<Gestionnaire>> getAllGestionnaires() {
        List<Gestionnaire>gestionnaires = gestionnaireService.getAllGestionnaires();
        return new ResponseEntity<>(gestionnaires, HttpStatus.OK);
    }
    @PutMapping("updateGest/{id}")
    public ResponseEntity<Gestionnaire> updateGestionnaire(@PathVariable Long id, @RequestBody Gestionnaire gestionnaire) {
        // Vérifiez si le client avec l'ID donné existe
        if (!gestionnaireService.getGestionnaireById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        gestionnaire.setId(id);
        Gestionnaire updatedGestionnaire = gestionnaireService.updateGestionnaire(gestionnaire);
        return new ResponseEntity<>(updatedGestionnaire, HttpStatus.OK);
    }

    @PutMapping("/updateRole/{gestionnaireId}")
    public ResponseEntity<String> updateGestionnaireRole(@PathVariable Long gestionnaireId,
                                                         @RequestBody ERole newRole) {

        // Vérifier si le gestionnaire existe dans la base de données
        Optional<Gestionnaire> optionalGestionnaire = gestionnaireRepository.findById(gestionnaireId);
        if (!optionalGestionnaire.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Récupérer le gestionnaire de la base de données
        Gestionnaire gestionnaire = optionalGestionnaire.get();

        // Vérifier si le nouveau rôle existe dans la base de données
        Optional<Role> optionalNewRole = roleRepository.findByName(newRole);
        if (!optionalNewRole.isPresent()) {
            return ResponseEntity.badRequest().body("Le rôle spécifié n'existe pas.");
        }

        // Mettre à jour le rôle du gestionnaire
        gestionnaire.setRoles(new HashSet<>(Collections.singletonList(optionalNewRole.get())));

        // Enregistrer les changements dans la base de données
        gestionnaireRepository.save(gestionnaire);

        return ResponseEntity.ok("Rôle du gestionnaire mis à jour avec succès.");
    }
    @DeleteMapping("/deleteGestionnaire/{id}")
    public ResponseEntity<Void> deleteGest(@PathVariable Long id) {
        // Check if the gestionnaire with the given ID exists
        Optional<Gestionnaire> optionalGestionnaire = gestionnaireService.getGestionnaireById(id);
        if (!optionalGestionnaire.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Gestionnaire exists, proceed with deletion
        gestionnaireService.deleteGestionnaireById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
    public void repondreReclamation(@PathVariable Long id, @RequestBody String message) throws Exception {
        Sinistre sinsitre = sinistreService.getSinistreById(id)
                .orElseThrow(() -> new Exception("sinsitre : " + id));



        // Récupérer l'utilisateur associé au Sinistre
        User user = sinsitre.getUser();

        // Créer une nouvelle instance de notification avec l'utilisateur et le contenu de la réponse
        Notification notification = new Notification();
        notification.setContenu(message);
        notification.setUser(user);

        // Enregistrer la notification dans la base de données
        notificationRepository.save(notification);
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

/*    @PostMapping("/sendEmail")
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

            // Customize the email content based on packType and typeOccupantNom
            String emailContent = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Devis</title>\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            color: #000;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "        .container {\n" +
                    "            border: 1px solid #ccc;\n" +
                    "            width: 794px;  /* A4 width */\n" +
                    "            height: 1123px;  /* A4 height */\n" +
                    "            margin: 0 auto;\n" +
                    "            padding: 20px;\n" +
                    "            background-color: #f9f9f9;\n" +
                    "            box-sizing: border-box;\n" +
                    "        }\n" +
                    "        .logo {\n" +
                    "            float: left;\n" +
                    "            margin-right: 20px;\n" +
                    "        }\n" +
                    "        .header {\n" +
                    "            text-align: center;\n" +
                    "            margin-bottom: 20px;\n" +
                    "        }\n" +
                    "        .header h1 {\n" +
                    "            font-size: 26px;\n" +
                    "            font-weight: bold;\n" +
                    "            color: #333;\n" +
                    "        }\n" +
                    "        .devis-details {\n" +
                    "            margin-top: 20px;\n" +
                    "            padding: 10px;\n" +
                    "            background-color: #fff;\n" +
                    "        }\n" +
                    "        .devis-details p {\n" +
                    "            margin: 10px 0;\n" +
                    "            font-size: 24px;\n" +
                    "        }\n" +
                    "        .footer {\n" +
                    "            margin-top: 20px;\n" +
                    "            background-color: #fff;\n" +
                    "            padding: 10px;\n" +
                    "        }\n" +
                    "        .footer h2 {\n" +
                    "            font-size: 24px;\n" +
                    "            margin-top: 0;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"header\">\n" +
                    "        <img src=\"https://www.assurancesami.com/sites/default/files/logo-ami-assurance.png\" alt=\"Company Logo\" width=\"200\" />\n" +
                    "        <h1>Devis</h1>\n" +
                    "    </div>\n" +
                    "    <div class=\"devis-details\">\n" +
                    "        <p><strong>Bonjour " + emailRequest.getName() + ",</strong></p>\n" +
                    "        <p>Nous vous remercions d'avoir choisi notre produit Multirisque Habitation. Voici un résumé de votre devis :</p>\n" +
                    "        <p><strong>Pack choisi :</strong> " +  emailRequest.getPackType() + "</p>\n" +
                    "        <p><strong>Type d'occupant :</strong> " + emailRequest.getTypeOccupantNom() + "</p>\n" +
                    "        <p><strong>Montant total en TTC :</strong> " +  emailRequest.getContent() + " dt </p>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        <h2>Montant total</h2>\n" +
                    "        <p>" + emailRequest.getContent() + "</p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

            helper.setText(emailContent, true); // Set the second parameter to true to indicate HTML content

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception
        }
    }



}
