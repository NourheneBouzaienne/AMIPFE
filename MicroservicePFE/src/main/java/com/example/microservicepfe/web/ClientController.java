package com.example.microservicepfe.web;


import com.example.microservicepfe.beans.Contrat;
import com.example.microservicepfe.proxies.MicroserviceContratProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/Client")
public class ClientController {
    private final MicroserviceContratProxy contratsProxy;

    public ClientController(MicroserviceContratProxy contratsProxy){
        this.contratsProxy = contratsProxy;
    }
    @RequestMapping("/Contrats")
    List<Contrat>  contrat (){List<Contrat> contrats =  contratsProxy.listeDesContrats();
        return  contrats;
    }
}