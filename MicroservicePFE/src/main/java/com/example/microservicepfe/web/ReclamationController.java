package com.example.microservicepfe.web;


import com.example.microservicepfe.dao.DemandeRepository;
import com.example.microservicepfe.dao.FcmTokenRepository;
import com.example.microservicepfe.dao.NotificationRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.FcmToken;
import com.example.microservicepfe.models.Notification;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.payload.request.EmailRequest;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import com.example.microservicepfe.services.FcmTokenService;
import com.google.firebase.messaging.FirebaseMessaging;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth/reclammation")
public class ReclamationController {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FcmTokenService fcmTokenService;
    @Autowired
    private FirebaseMessaging firebaseMessaging;
    @Autowired
    private  FcmTokenRepository fcmTokenRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/reclammations")
    public List<Demande> getReclammations (@AuthenticationPrincipal UserDetailsImpl userDetails) {
       Long userId = userDetails.getId(); // Récupération de l'ID de l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId)); // Récupération de l'utilisateur à partir de l'ID
        return demandeRepository.findAll(); // Récupération des demandes
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/{reclamationId}")
    public ResponseEntity<Demande> getReclamationById(@PathVariable Long reclamationId) {
        Optional<Demande> demandeOptional = demandeRepository.findById(reclamationId);
        if (demandeOptional.isPresent()) {
            Demande demande = demandeOptional.get();
            return new ResponseEntity<>(demande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PutMapping("/{reclamationId}")
    public ResponseEntity<Demande> updateEtatReclamation(@PathVariable Long reclamationId, @RequestBody Demande updatedDemande) {
        Optional<Demande> demandeOptional = demandeRepository.findById(reclamationId);
        if (demandeOptional.isPresent()) {
            Demande existingDemande = demandeOptional.get();

            // Mettre à jour les propriétés de la demande existante avec les nouvelles valeurs
            existingDemande.setEtat(updatedDemande.getEtat());

            Demande updatedReclamation = demandeRepository.save(existingDemande);

            return new ResponseEntity<>(updatedReclamation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/{id}/repondre")
    public void repondreReclamation(@PathVariable("id") Long reclamationId, @RequestBody String message) throws Exception {
        Demande reclamation = demandeRepository.findById(reclamationId)
                .orElseThrow(() -> new Exception("Réclamation non trouvée : " + reclamationId));

        // Enregistrer la réponse dans la base de données
        reclamation.setReponse(message);
        demandeRepository.save(reclamation);

        // Récupérer l'utilisateur associé à la réclamation
        User user = reclamation.getUser();

        // Créer une nouvelle instance de notification avec l'utilisateur et le contenu de la réponse
        Notification notification = new Notification();
        notification.setContenu(message);
        notification.setUser(user);

        // Enregistrer la notification dans la base de données
        notificationRepository.save(notification);

        //Long userId = user.getId(); // Récupération de l'ID de l'utilisateur à partir de la table user
        //String userFcmToken = fcmTokenService.getFcmTokenByUserId(userId);

        // Créer le message de notification
        //Message notificationMessage = Message.builder()
          //      .setToken(userFcmToken)
           //     .putData("title", "Nouvelle réponse à votre réclamation")
             //   .putData("body", message)
               // .build();

        // Envoyer la notification
        //String response = firebaseMessaging.send(notificationMessage);
        //System.out.println("Notification envoyée : " + response);

    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification() {
        // Get the Expo push token from the request
        //String expoPushToken = request.getExpoPushToken();

        // Create the notification message
        String notificationMessage = "{\"to\": \"" + "ExponentPushToken[zsxwz-CzDHrncotyJGGrIk]" + "\", \"title\": \"New Notification\", \"body\": \"This is a test notification\"}";

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Set up the request entity
        HttpEntity<String> entity = new HttpEntity<>(notificationMessage, headers);

        // Send the push notification using Expo's API
        String expoPushApiUrl = "https://exp.host/--/api/v2/push/send";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(expoPushApiUrl, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok("Notification sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification");
        }
    }


    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/{id}/repondreWithNotification")
    public void repondreReclamationWithNotification(@PathVariable("id") Long reclamationId, @RequestBody String message) throws Exception {
        Demande reclamation = demandeRepository.findById(reclamationId)
                .orElseThrow(() -> new Exception("Réclamation non trouvée : " + reclamationId));

        // Enregistrer la réponse dans la base de données
        reclamation.setReponse(message);
        demandeRepository.save(reclamation);

        // Récupérer l'utilisateur associé à la réclamation
        User user = reclamation.getUser();
      // Créer une nouvelle instance de notification avec l'utilisateur et le contenu de la réponse
        Notification notification = new Notification();
        notification.setContenu(message);
        notification.setTitle(reclamation.getCateg());

        notification.setUser(user);

        // Enregistrer la notification dans la base de données
        notificationRepository.save(notification);
        // Récupérer le token Expo push de l'utilisateur depuis la base de données
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
        notificationData.put("title", "Nouvelle réponse à votre réclamation");
        notificationData.put("body", contenu);

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






    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getEmail());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getContent());

        javaMailSender.send(message);
    }


}
