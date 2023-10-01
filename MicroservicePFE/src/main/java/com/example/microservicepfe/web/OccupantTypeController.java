package com.example.microservicepfe.web;

import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.TypeOccupant;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import com.example.microservicepfe.services.OccupantTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/occupants")
public class OccupantTypeController {

    private final OccupantTypeService occupantTypeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public OccupantTypeController(OccupantTypeService occupantTypeService) {
        this.occupantTypeService = occupantTypeService;
    }

    @GetMapping
    public ResponseEntity<List<TypeOccupant>> getAllOccupantTypes(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String CIN = userDetails.getUsername();
        User user = userRepository.findUserByUsername(CIN);
        List<TypeOccupant> occupantTypes = occupantTypeService.getAllOccupantTypes();
        return ResponseEntity.ok(occupantTypes);
    }
}
