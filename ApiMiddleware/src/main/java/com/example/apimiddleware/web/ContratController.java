package com.example.apimiddleware.web;

import com.example.apimiddleware.beans.Contrat;
import com.example.apimiddleware.proxies.MicroserviceContratProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Contrat")
public class ContratController {
    private final MicroserviceContratProxy contratsProxy;

    public ContratController(MicroserviceContratProxy contratsProxy){
        this.contratsProxy = contratsProxy;
    }
    @RequestMapping("/contrats")
    List<Contrat> contrat (){
        List<Contrat> contrats =  contratsProxy.listeDesContrats();

         return  contrats; }

    @GetMapping("/listContratsClients")
    public List<Contrat> getContratsClient(@RequestParam String CIN, @RequestParam String numCNT) {
        List<Contrat> contratsClient =   contratsProxy.ContratsClient(CIN, numCNT) ;
        return contratsClient ;
    }


    @GetMapping("/listContratsByClients")
    public List<Contrat> getContratsByClient(@RequestParam String CIN) {
        List<Contrat> contratsClient =   contratsProxy.ContratsByClient(CIN);
        return contratsClient ;
    }

    @GetMapping("/getContratByNUMCNT")
    public ResponseEntity<Contrat>  getContratByNumCNT(@RequestParam String numCNT) {
        Optional<Contrat> contratByNUMCNTOptional =   contratsProxy.getContratByNUM(numCNT);

        if (contratByNUMCNTOptional.isPresent()) {
            Contrat contrat = contratByNUMCNTOptional.get();
            return new ResponseEntity<>(contrat, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getGRNTByNUMCNT")
    public List<Contrat> getGRNTByCNTclient(@RequestParam String numCNT) {
        List<Contrat> garanties =   contratsProxy.GarantiesByCNT(numCNT);
        return  garanties ;
    }






}
