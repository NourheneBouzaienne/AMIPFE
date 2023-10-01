package com.example.microservicepfe.proxies;

import com.example.microservicepfe.beans.Contrat;
import com.example.microservicepfe.beans.Quittance;
import com.example.microservicepfe.beans.SinistreBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ApiMiddleware", url = "localhost:8090")
public interface MiddlewareProxy {
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

    @GetMapping(value ="Sinistre/listSinsitresByClients")
    List<SinistreBean> getSinistresByClient(@RequestParam String CIN) ;


    @GetMapping(value ="Sinistre/getSinistreByNUMSNT")
    Optional<SinistreBean> getSinistreByNUMSNT(@RequestParam String CIN, @RequestParam String NUMSNT) ;

    @GetMapping(value ="Contrat/listContratSinistreByID")
    List<Contrat> ContratSinistreByID (@RequestParam String CIN) ;

    @GetMapping(value = "Contrat/getRemorquage")
    Optional<Contrat> getRemorquage(@RequestParam String numCNT) ;

    @GetMapping(value = "Contrat/proposeGarantie")
    List<Contrat> proposeGarantie(@RequestParam String numCNT) ;

    @GetMapping(value ="Quittance/listQuittanceNonP")
    List<Quittance> QuittanceByID (@RequestParam String CIN) ;

    @GetMapping(value ="Quittance/listQuittances")
    List<Quittance> ListQuittances (@RequestParam String CIN) ;

}