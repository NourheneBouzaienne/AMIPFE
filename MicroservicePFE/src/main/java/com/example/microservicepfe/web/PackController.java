package com.example.microservicepfe.web;

import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Pack;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import com.example.microservicepfe.services.PackService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/packs")
public class PackController {

    private final PackService packService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public PackController(PackService packService) {
        this.packService = packService;
    }

    @GetMapping
    public ResponseEntity<List<Pack>> getAllPacks(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String CIN = userDetails.getUsername();
        User user = userRepository.findUserByUsername(CIN);
        List<Pack> packs = packService.getAllPacks();
        return ResponseEntity.ok(packs);
    }
}
