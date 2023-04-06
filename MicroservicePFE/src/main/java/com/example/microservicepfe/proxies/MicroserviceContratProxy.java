package com.example.microservicepfe.proxies;

import com.example.microservicepfe.beans.Contrat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignClient(name = "ApiMiddleware", url = "localhost:8090")
public interface MicroserviceContratProxy {
    @GetMapping(value = "Contrat/contrats")
    List<Contrat> listeDesContrats();


    /*@GetMapping( value = "/Contrat/{id}")
    Contrat recupererUnContrat(@PathVariable("id") int id);
*/
}