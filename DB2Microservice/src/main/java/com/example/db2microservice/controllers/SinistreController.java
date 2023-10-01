package com.example.db2microservice.controllers;


import com.example.db2microservice.dao.ContratDAO;
import com.example.db2microservice.dao.SinistreDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/Sinistre")
public class SinistreController {

    private final SinistreDAO sinsitreDAO;

    public SinistreController(SinistreDAO sinsitreDAO) {
        this.sinsitreDAO = sinsitreDAO;
    }

    @GetMapping("/listSinistresByClients")
    public List<Map<String, Object>> SinistresByClients(@RequestParam String CIN) {
        return sinsitreDAO.SinistreByID(CIN);
    }


    @GetMapping("/getSinistreByNUMSNT")
    public Optional<Map<String, Object>> sinistreByNUMSNT(@RequestParam String NUMSNT) {
        return sinsitreDAO.SinistreByNUMSNT(NUMSNT);
    }
}
