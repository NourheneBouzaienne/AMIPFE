package com.example.microservicepfe.web;


import com.example.microservicepfe.beans.Contrat;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.proxies.MicroserviceContratProxy;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/Client")
public class ClientController {
    private final MicroserviceContratProxy contratsProxy;
    @Autowired
    UserRepository userRepository;

    public ClientController(MicroserviceContratProxy contratsProxy){
        this.contratsProxy = contratsProxy;
    }
    @RequestMapping("/Contrats")
    List<Contrat>  contrat (){List<Contrat> contrats =  contratsProxy.listeDesContrats();
        return  contrats;
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping("/ContratsClient")
    List<Contrat> getContratsClient( @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur
        List<Contrat> contratsClient =   contratsProxy.getContratsClient(CIN, numCNT) ;
        return contratsClient ;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Authentication authentication) {
        // Vérifier que l'utilisateur est authentifié
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Récupérer l'utilisateur connecté
        String username = authentication.getName();
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(user);
    }
    @PostMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestBody User updatedUser, Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }


        String username = authentication.getName();
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        //user.setAdresse(updatedUser.getAdresse());
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        //user.setNumTel(updatedUser.getNumTel());
        user.setUsername(updatedUser.getUsername());


        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}