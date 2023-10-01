package com.example.microservicepfe.web;


import com.example.microservicepfe.beans.Quittance;
import com.example.microservicepfe.beans.SinistreBean;
import com.example.microservicepfe.proxies.MiddlewareProxy;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/Quittance")
public class QuittanceController {

    private final MiddlewareProxy quittanceProxy;

    public QuittanceController(MiddlewareProxy quittanceProxy) {
        this.quittanceProxy = quittanceProxy;
    }

    @GetMapping("/listQuittanceNonP")
    public List<Quittance> getQuittancesClient(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String CIN = userDetails.getUsername();

        List<Quittance> quittancesClient =   quittanceProxy.QuittanceByID(CIN) ;
        return quittancesClient ;
    }

    @GetMapping("/listQuittances")
    public List<Quittance> listQuittances(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String CIN = userDetails.getUsername();

        List<Quittance> quittances =   quittanceProxy.ListQuittances(CIN) ;
        return quittances ;
    }


}
