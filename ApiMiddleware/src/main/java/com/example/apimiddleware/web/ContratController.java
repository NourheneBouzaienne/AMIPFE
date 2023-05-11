package com.example.apimiddleware.web;

import com.example.apimiddleware.beans.Contrat;
import com.example.apimiddleware.proxies.MicroserviceContratProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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



}
