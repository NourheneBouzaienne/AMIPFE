package com.example.apimiddleware.web;

import com.example.apimiddleware.beans.Contrat;
import com.example.apimiddleware.beans.Sinistre;
import com.example.apimiddleware.proxies.MicroserviceContratProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Sinistre")
public class SinistreController {
    private final MicroserviceContratProxy sinsitreProxy;

    public SinistreController(MicroserviceContratProxy sinsitreProxy) {
        this.sinsitreProxy = sinsitreProxy;
    }

    @GetMapping("/listSinsitresByClients")
    public List<Sinistre> getSinistresByClient(@RequestParam String CIN) {
        List<Sinistre> sinistresClient =   sinsitreProxy.SinistresByClient(CIN);
        return sinistresClient ;
    }

    @GetMapping("/getSinistreByNUMSNT")
    public ResponseEntity<Sinistre> getSinistreByNUMSNT(@RequestParam String NUMSNT) {
        Optional<Sinistre> sinistreByNUMSNT =   sinsitreProxy.sinistreByNUMSNT(NUMSNT);

        if (sinistreByNUMSNT.isPresent()) {
            Sinistre sinistre = sinistreByNUMSNT.get();
            return new ResponseEntity<>(sinistre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
