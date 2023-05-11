package com.example.apimiddleware.proxies;

import com.example.apimiddleware.beans.Contrat;
import com.example.apimiddleware.beans.ContratClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "DB2Microservice", url = "localhost:8000")
public interface MicroserviceContratProxy {
    @GetMapping(value = "api/listContrats")
    List<Contrat> listeDesContrats();


    /*@GetMapping( value = "/Contrat/{id}")
    Contrat recupererUnContrat(@PathVariable("id") int id);
*/

    @GetMapping(value ="api/listContratsClients")
   List<Contrat> ContratsClient(@RequestParam String CIN, @RequestParam String numCNT) ;





}