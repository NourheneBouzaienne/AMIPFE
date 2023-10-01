package com.example.microservicepfe.web;

import com.example.microservicepfe.dao.DevisRepository;
import com.example.microservicepfe.dao.PackRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.*;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import com.example.microservicepfe.services.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/devis")
public class DevisController {
    private final DevisService devisService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DevisRepository devisRepository;


    @Autowired
    private PackRepository packRepository;
    public DevisController(DevisService devisService) {
        this.devisService = devisService;
    }
    @PostMapping("/multiRisqueHabitation")
    public Devis createMultiRisqueHabitationDevis(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody MultiRisqueHabitation multiRisqueHabitation) {
        String CIN = userDetails.getUsername();
        User user = userRepository.findUserByUsername(CIN);

        // Récupérer le pack sélectionné à partir de la requête (vous devez adapter cela selon comment vous envoyez le pack depuis le frontend)
        Long selectedPackId = multiRisqueHabitation.getPack().getId();
        Pack pack = packRepository.findById(selectedPackId).orElse(null);

        // Vérifier si le pack a été trouvé
        if (pack == null) {
           System.out.println(pack);
        }

        // Affecter le pack à l'objet MultiRisqueHabitation
        multiRisqueHabitation.setPack(pack);
        // Affecter l'utilisateur authentifié à la propriété user
        multiRisqueHabitation.setUser(user);
        String  categ = "Multirisque Habitation";

        multiRisqueHabitation.setCateg(categ);

        return devisService.createMultiRisqueHabitationDevis(multiRisqueHabitation);
    }



  /*  @GetMapping("/garanties")
    public ResponseEntity<List<Garantie>> getGaranties(@RequestParam String occupantType, @RequestParam String packType) {
        List<Garantie> garanties = devisService.getGarantiesByOccupantTypeAndPackType(occupantType, packType);
        return ResponseEntity.ok(garanties);
    }*/
  @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @GetMapping("/devis")
    public List<Devis> getDevisUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId(); // Récupération de l'ID de l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId)); // Récupération de l'utilisateur à partir de l'ID
        return devisRepository.findByUser(user); // Récupération des demandes associées à l'utilisateur
    }
}