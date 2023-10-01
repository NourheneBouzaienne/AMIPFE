package com.example.microservicepfe.web;


import com.example.microservicepfe.dao.DemandeGarantieRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.DemandeGarantie;
import com.example.microservicepfe.models.Devis;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/DemandeGarantie")
public class DemandeGarantieController {
    @Autowired
    private  DemandeGarantieRepository demandeGarantieRepository;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/addDemande")
    public DemandeGarantie addDemande(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody DemandeGarantie demande) {
        String CIN = userDetails.getUsername();
        User user = userRepository.findUserByUsername(CIN);
        demande.setUser(user);
        return demandeGarantieRepository.save(demande);
    }
    @GetMapping("/Garanties")
    public ResponseEntity<List<DemandeGarantie>> getAllGaranties() {
        List<DemandeGarantie> demandes =demandeGarantieRepository.findAll();
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }

}
