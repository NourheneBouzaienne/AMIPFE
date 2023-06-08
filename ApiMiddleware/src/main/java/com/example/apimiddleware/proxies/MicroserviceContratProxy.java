package com.example.apimiddleware.proxies;

import com.example.apimiddleware.beans.Contrat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "DB2Microservice", url = "localhost:8000")
public interface MicroserviceContratProxy {
    @GetMapping(value = "api/listContrats")
    List<Contrat> listeDesContrats();


    /*@GetMapping( value = "/Contrat/{id}")
    Contrat recupererUnContrat(@PathVariable("id") int id);
*/

    @GetMapping(value ="api/listContratsClients")
   List<Contrat> ContratsClient(@RequestParam String CIN, @RequestParam String numCNT) ;

    @GetMapping(value ="api/listContratsByClients")
    List<Contrat> ContratsByClient(@RequestParam String CIN) ;

    @GetMapping(value ="api/getContartByNUM")
    Optional<Contrat> getContratByNUM(@RequestParam String numCNT) ;


    @GetMapping(value ="api/getGRNTByNUMCNT")
    List<Contrat> GarantiesByCNT(@RequestParam String numCNT) ;



}