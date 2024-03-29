package com.example.microservicepfe.web;


import com.example.microservicepfe.beans.Contrat;
import com.example.microservicepfe.dao.ClientRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Client;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.proxies.MiddlewareProxy;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/Client")
public class ClientController {
    private final MiddlewareProxy middlewareProxy;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientRepository clientRepository;

    public ClientController(MiddlewareProxy middlewareProxy) {
        this.middlewareProxy = middlewareProxy;
    }

    @RequestMapping("/Contrats")
    List<Contrat>  contrat (){List<Contrat> contrats =  middlewareProxy.listeDesContrats();
        return  contrats;
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping("/ContratsClientV1")
    List<Contrat> getContratsClient( @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur
        List<Contrat> contratsClient =   middlewareProxy.getContratsClient(CIN, numCNT) ;

        return contratsClient ;
    }

    @GetMapping("/ContratsSinistre")
    List<Contrat> getContratsSinistre( @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur
        List<Contrat> contratsClient =   middlewareProxy.ContratSinistreByID(CIN) ;

        return contratsClient ;
    }

    @GetMapping("/getContratByNUMCNT")
    public ResponseEntity <Contrat> getContratByNUMCNT (@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestParam String numCNT) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur

        //User client = userRepository.findUserByUsername(CIN);

        Optional<Contrat> contratByNUMCNTOptional =  middlewareProxy.getContratByNUMCNT(CIN,numCNT);

        if (contratByNUMCNTOptional.isPresent()) {
            Contrat contrat = contratByNUMCNTOptional.get();
            return new ResponseEntity<>(contrat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getRemorquage")
    public ResponseEntity<Boolean> getRemorquage (@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestParam String numCNT) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur

        //User client = userRepository.findUserByUsername(CIN);

        Optional<Contrat> contratByNUMCNTOptional = middlewareProxy.getRemorquage(numCNT);

        if (contratByNUMCNTOptional.isPresent()) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }


    @GetMapping("/ContratsClient")
    List<Contrat> addContratsClient( @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur

        User client = userRepository.findUserByUsername(CIN);


        List<Contrat> contratsClient =   middlewareProxy.getContratsClient(CIN, numCNT) ;

        if (client != null && !client.isAuthentificated() && contratsClient!= null ) {
            client.setAuthentificated(true);
            userRepository.save(client);
        }
        return contratsClient ;

    }
    @GetMapping("/getGRNTByNUMCNT")
    List<Contrat> getGRNTByNUMCNT( @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String numCNT) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur
        //User client = userRepository.findUserByUsername(CIN);
        List<Contrat> garanties =   middlewareProxy.getGRNTByCNTclient(CIN, numCNT) ;
        return garanties ;
    }
    @GetMapping("/proposeGarantie")
    List<Contrat> proposeGarantie(@RequestParam String numCNT) {
        //String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur
        //User client = userRepository.findUserByUsername(CIN);
        List<Contrat> garanties =   middlewareProxy.proposeGarantie(numCNT) ;
        return garanties ;
    }

    @GetMapping("/ContratsByClient")
    List<Contrat> ContratsByClient( @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur

        User client = userRepository.findUserByUsername(CIN);

        List<Contrat> contratsClient;

        if (client != null && client.isAuthentificated()) {
            contratsClient = middlewareProxy.getContratsByClient(CIN);
        } else {
            contratsClient = new ArrayList<>(); // Liste vide
        }

        return contratsClient;
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
    public ResponseEntity<Client> updateProfile(@RequestBody Client updatedUser, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String username = userDetails.getUsername(); // Récupération CIN  de l'utilisateur
        Client client = clientRepository.findClientByUsername(username);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        client.setAdresse(updatedUser.getAdresse());
        client.setName(updatedUser.getName());
        client.setEmail(updatedUser.getEmail());
        client.setNumTel(updatedUser.getNumTel());
        //user.setUsername(updatedUser.getUsername());


        clientRepository.save(client);
        return ResponseEntity.ok().build();
    }
}