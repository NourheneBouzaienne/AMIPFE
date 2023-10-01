package com.example.microservicepfe.web;

import com.example.microservicepfe.beans.Contrat;
import com.example.microservicepfe.beans.SinistreBean;
import com.example.microservicepfe.dao.ClientRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Sinistre;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.proxies.MiddlewareProxy;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import com.example.microservicepfe.services.SinistreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/auth/sinistre")
public class SinistreController {

    @Autowired
    private SinistreService sinistreService;

    private final MiddlewareProxy middlewareProxy;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClientRepository clientRepository;

    public SinistreController(MiddlewareProxy middlewareProxy) {
        this.middlewareProxy = middlewareProxy;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

    @PostMapping(value = "/addSinistre", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Sinistre> createSinistre(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestParam("numCnt") String numCnt,
                                                   @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("latitude") double latitude,
                                                   @RequestParam("longitude") double longitude,
                                                   @RequestPart("constatPhotos") List<MultipartFile> constatPhotos,
                                                   @RequestPart("sinistrePhotos") List<MultipartFile> sinistrePhotos) throws IOException {
        String CIN = userDetails.getUsername();
        User user = userRepository.findUserByUsername(CIN);
            // Créer un objet Sinistre en utilisant les champs récupérés
        String referenceCode = UUID.randomUUID().toString();
            Sinistre sinistre = new Sinistre();
            sinistre.setNumCnt(numCnt);
            sinistre.setDate(date);
            sinistre.setDescription(description);
            sinistre.setLatitude(latitude);
            sinistre.setLongitude(longitude);
            sinistre.setLieu(latitude,latitude);
            sinistre.setReferenceCode(referenceCode);
            sinistre.setUser(user);


        Sinistre createdSinistre = sinistreService.createSinistre(sinistre,constatPhotos, sinistrePhotos);
            //return ResponseEntity.ok(createdSinistre);
        return ResponseEntity.ok().body(createdSinistre);


    }

    @GetMapping("/SinistresByClient")
    List<SinistreBean> SinistresByClient(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String CIN = userDetails.getUsername();
        List<SinistreBean> sinsitresClient =   middlewareProxy.getSinistresByClient(CIN) ;
        return sinsitresClient;
    }
    @GetMapping("/getSinistreByNUMSNT")
    public ResponseEntity <SinistreBean> getSinistreByNUMSNT (@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String NUMSNT) {
        String CIN = userDetails.getUsername(); // Récupération CIN  de l'utilisateur

        //User client = userRepository.findUserByUsername(CIN);

        Optional<SinistreBean> contratByNUMCNTOptional =  middlewareProxy.getSinistreByNUMSNT(CIN,NUMSNT);

        if (contratByNUMCNTOptional.isPresent()) {
            SinistreBean sinsitre = contratByNUMCNTOptional.get();
            return new ResponseEntity<>(sinsitre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
