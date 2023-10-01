package com.example.microservicepfe.web;


import com.example.microservicepfe.dao.DemandeRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/demande")
public class DemandeController {
    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private UserRepository userRepository;


    @CrossOrigin(origins = "*")
    @GetMapping("/demandes")
    public List<Demande> getDemandesUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId(); // Récupération de l'ID de l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId)); // Récupération de l'utilisateur à partir de l'ID
        return demandeRepository.findByUser(user); // Récupération des demandes associées à l'utilisateur
    }


    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

    @PostMapping("/addDemande")
    public Demande addDemande(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody Demande demande) {
        String CIN = userDetails.getUsername();
        User user = userRepository.findUserByUsername(CIN);
        demande.setUser(user);
        return demandeRepository.save(demande);
    }

    @GetMapping("/getDemande/{demandeId}")
    public ResponseEntity<Demande> getDemandeById(@PathVariable Long demandeId) {
        Optional<Demande> demandeOptional = demandeRepository.findById(demandeId);
        if (demandeOptional.isPresent()) {
            Demande demande = demandeOptional.get();
            return new ResponseEntity<>(demande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
