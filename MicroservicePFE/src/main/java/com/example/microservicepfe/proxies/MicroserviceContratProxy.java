package com.example.microservicepfe.proxies;

import com.example.microservicepfe.beans.Contrat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ApiMiddleware", url = "localhost:8090")
public interface MicroserviceContratProxy {
    @GetMapping(value = "Contrat/contrats")
    List<Contrat> listeDesContrats();


    /*@GetMapping( value = "/Contrat/{id}")
    Contrat recupererUnContrat(@PathVariable("id") int id);
*/

    @GetMapping(value = "Contrat/listContratsClients")
     List<Contrat> getContratsClient(@RequestParam String CIN, @RequestParam String numCNT) ;


    @GetMapping(value = "Contrat/listContratsByClients")
    List<Contrat> getContratsByClient(@RequestParam String CIN) ;

    @GetMapping(value = "Contrat/getContratByNUMCNT")
    Optional<Contrat> getContratByNUMCNT(@RequestParam String CIN,@RequestParam String numCNT) ;

    @GetMapping(value = "Contrat/getGRNTByNUMCNT")
    List<Contrat> getGRNTByCNTclient(@RequestParam String CIN,@RequestParam String numCNT) ;





}