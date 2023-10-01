package com.example.apimiddleware.web;


import com.example.apimiddleware.beans.Contrat;
import com.example.apimiddleware.beans.Quittance;
import com.example.apimiddleware.proxies.MicroserviceContratProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Quittance")
public class QuittanceController {

    private final MicroserviceContratProxy quittanceProxy;

    public QuittanceController(MicroserviceContratProxy quittanceProxy) {
        this.quittanceProxy = quittanceProxy;
    }

    @GetMapping("/listQuittanceNonP")
    public List<Quittance> getQuittancesClient(@RequestParam String CIN) {
        List<Quittance> quittancesClient =   quittanceProxy.QuittanceByID(CIN) ;
        return quittancesClient ;
    }

    @GetMapping("/listQuittances")
    public List<Quittance> ListQuittances (@RequestParam String CIN) {
        List<Quittance> quittances =   quittanceProxy.ListQuittances(CIN) ;
        return quittances ;
    }


}
