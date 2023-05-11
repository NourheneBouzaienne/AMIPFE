package com.example.db2microservice.controllers;

import com.example.db2microservice.dao.ContratDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
        import java.util.Map;
@RestController
@RequestMapping("/api")
public class ContratController {
    private final ContratDAO contratDAO;

    public ContratController(ContratDAO contratDAO) {
        this.contratDAO = contratDAO;
    }

    @GetMapping("/listContrats")
    public List<Map<String, Object>> findAll() {
        return contratDAO.findAll();
    }

    @GetMapping("/listContratsClients")
    public List<Map<String, Object>> ContratsClient(@RequestParam String CIN, @RequestParam String numCNT) {
        return contratDAO.findByIDAndNUMCNT(CIN, numCNT);
    }
}