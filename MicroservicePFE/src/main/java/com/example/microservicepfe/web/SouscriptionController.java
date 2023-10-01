package com.example.microservicepfe.web;

import com.example.microservicepfe.security.services.EmailService;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/souscription")
public class SouscriptionController {

    private final EmailService emailService;

    @Autowired
    public SouscriptionController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/effectuer")
    public void effectuerSouscription(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String email = userDetails.getEmail();
        emailService.sendConfirmationEmail(email);
    }
}
