package com.example.microservicepfe.web;


import com.example.microservicepfe.dao.NotificationRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.Notification;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import com.example.microservicepfe.services.FcmTokenService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FcmTokenService fcmTokenService;

    @Autowired
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public List<Notification> getNotificationsForCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId(); // Récupération de l'ID de l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId)); // Récupération de l'utilisateur à partir de l'ID
        return notificationRepository.findByUser(user);
    }


    @PostMapping("/{userId}/send-notification")
    public ResponseEntity<String> sendNotification(@PathVariable("userId") Long userId, @RequestBody String message) {
        // Récupérez le token FCM de l'utilisateur depuis votre base de données ou le FcmTokenService
        String fcmToken = fcmTokenService.getFcmTokenByUserId(userId);

        // Envoyez la notification en utilisant l'API REST de OneSignal
        String apiUrl = "https://onesignal.com/api/v1/notifications";
        String appId = "5080cbfd-767a-475d-ad79-580f76fb3bb3";
        String apiKey = "MGE3NjJmZDQtOWRkZS00ZDA5LWI1OWEtNmU1NjVhMmFhNWIw";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + apiKey);
        headers.set("Content-Type", "application/json");
        String encodedMessage = new Gson().toJson(message);
        String payload = String.format("{\"app_id\":\"%s\",\"include_player_ids\":[\"%s\"],\"contents\":{\"en\":%s}}",
                appId, fcmToken, encodedMessage);
        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Notification envoyée avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Échec de l'envoi de la notification");
        }
    }


}