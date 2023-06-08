package com.example.db2microservice.controllers;

import com.example.db2microservice.dao.ContratDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
        import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/listContratsByClients")
    public List<Map<String, Object>> ContratsByClients(@RequestParam String CIN) {
        return contratDAO.findByID(CIN);
    }

    @GetMapping("/getContartByNUM")
    public Optional<Map<String, Object>> getContratByNUM(@RequestParam String numCNT) {
        return contratDAO.findByNUMCNT(numCNT);
    }

    @GetMapping("/getGRNTByNUMCNT")
    public List<Map<String, Object>> getGRNTByNUMCNT(@RequestParam String numCNT) {
        return contratDAO.findGRNTByNUMCNT(numCNT);
    }
}