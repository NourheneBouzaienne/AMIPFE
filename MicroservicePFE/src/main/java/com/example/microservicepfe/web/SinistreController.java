package com.example.microservicepfe.web;

import com.example.microservicepfe.models.Sinistre;
import com.example.microservicepfe.services.SinistreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/auth/sinistre")
public class SinistreController {

    @Autowired
    private SinistreService sinistreService;



    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

    @PostMapping(value = "/addSinistre", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Sinistre> createSinistre(@RequestParam("numCnt") String numCnt,
                                                   @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                   @RequestParam("description") String description,
                                                   @RequestParam("latitude") double latitude,
                                                   @RequestParam("longitude") double longitude,
                                                   @RequestPart("constatPhotos") List<MultipartFile> constatPhotos,
                                                   @RequestPart("sinistrePhotos") List<MultipartFile> sinistrePhotos) throws IOException {

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



        Sinistre createdSinistre = sinistreService.createSinistre(sinistre,constatPhotos, sinistrePhotos);
            //return ResponseEntity.ok(createdSinistre);
        return ResponseEntity.ok().body(createdSinistre);


    }


}
