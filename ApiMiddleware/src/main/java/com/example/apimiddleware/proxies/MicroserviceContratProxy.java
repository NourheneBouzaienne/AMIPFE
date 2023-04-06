package com.example.apimiddleware.proxies;

import com.example.apimiddleware.beans.Contrat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "DB2Microservice", url = "localhost:8000")
public interface MicroserviceContratProxy {
    @GetMapping(value = "api/listContrats")
    List<Contrat> listeDesContrats();


    /*@GetMapping( value = "/Contrat/{id}")
    Contrat recupererUnContrat(@PathVariable("id") int id);
*/
}