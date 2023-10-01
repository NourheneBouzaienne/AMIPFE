package com.example.microservicepfe.web;


import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.GarantieParametrage;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import com.example.microservicepfe.services.GarantieParametrageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/garanties")
public class GarantieParametrageController {

    private final GarantieParametrageService garantieParametrageService;
    @Autowired
    private UserRepository userRepository;
    public GarantieParametrageController(GarantieParametrageService garantieParametrageService) {
        this.garantieParametrageService = garantieParametrageService;
    }

    @GetMapping("/{typeOccupantId}/{packId}")
    public ResponseEntity<List<GarantieParametrage>> getGarantiesByTypeOccupantAndPack(@AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long typeOccupantId,
            @PathVariable Long packId
    ) {
        String CIN = userDetails.getUsername();
        User user = userRepository.findUserByUsername(CIN);
        List<GarantieParametrage> garanties = garantieParametrageService.getGarantiesByTypeOccupantAndPack(typeOccupantId, packId);
        return ResponseEntity.ok(garanties);
    }
}
