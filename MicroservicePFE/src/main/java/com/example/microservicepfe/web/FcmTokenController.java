package com.example.microservicepfe.web;

import com.example.microservicepfe.dao.FcmTokenRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.FcmToken;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.payload.request.FcmTokenRequest;
import com.example.microservicepfe.services.DevisService;
import com.example.microservicepfe.services.FcmTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.microservicepfe.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/fcmTokens")
public class FcmTokenController {
    private final FcmTokenRepository fcmTokenRepository;
    private final FcmTokenService fcmTokenService;
    @Autowired
    private UserRepository userRepository;


    public FcmTokenController(FcmTokenRepository fcmTokenRepository, FcmTokenService fcmTokenService) {
        this.fcmTokenRepository = fcmTokenRepository;
        this.fcmTokenService = fcmTokenService;
    }



    @PostMapping("/registerFcmToken")
    public ResponseEntity<?> registerFcmToken(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody FcmTokenRequest request) {
        Long userId = userDetails.getId(); // Récupération de l'ID de l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId)); // Récupération de l'utilisateur à partir de l'ID

        // Enregistrement du token FCM associé à l'utilisateur dans la base de données
        fcmTokenService.saveFcmTokenForUser(userId, request.getToken());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}