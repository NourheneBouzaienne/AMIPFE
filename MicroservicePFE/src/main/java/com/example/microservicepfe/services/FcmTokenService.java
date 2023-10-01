package com.example.microservicepfe.services;

import com.example.microservicepfe.dao.FcmTokenRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.FcmToken;
import com.example.microservicepfe.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import javax.persistence.EntityNotFoundException;

@Service
public class FcmTokenService {
    private final FcmTokenRepository fcmTokenRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    public FcmTokenService(FcmTokenRepository fcmTokenRepository) {
        this.fcmTokenRepository = fcmTokenRepository;
    }

    // Méthode pour enregistrer le token FCM d'un utilisateur dans la base de données
    public void saveFcmTokenForUser(Long userId, String fcmToken) {
        // Récupérer l'utilisateur correspondant à l'ID de l'utilisateur depuis la base de données
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé : " + userId));

        // Créer une nouvelle instance de FcmToken
        FcmToken fcmTokenEntity = new FcmToken();
        fcmTokenEntity.setToken(fcmToken);
        // Associer l'utilisateur à l'instance FcmToken
        fcmTokenEntity.setUser(user);

        // Enregistrer l'instance FcmToken dans la base de données
        fcmTokenRepository.save(fcmTokenEntity);
    }


    // Méthode pour récupérer le token FCM de l'utilisateur en fonction de son ID
    public String getFcmTokenByUserId(Long userId) {
        FcmToken token = fcmTokenRepository.findById(userId).orElse(null);
        return (token != null) ? token.getToken() : null;
    }
}