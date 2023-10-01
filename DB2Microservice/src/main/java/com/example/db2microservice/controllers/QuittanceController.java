package com.example.db2microservice.controllers;


import com.example.db2microservice.dao.ContratDAO;
import com.example.db2microservice.dao.QuittanceDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Quittance")
public class QuittanceController {

    private final QuittanceDAO quittanceDAODAO;

    public QuittanceController(QuittanceDAO quittanceDAODAO) {
        this.quittanceDAODAO = quittanceDAODAO;
    }

    @GetMapping("/listQuittanceNonP")
    public List<Map<String, Object>> QuittanceByClients(@RequestParam String CIN) {
        return quittanceDAODAO.QuittanceByID(CIN);
    }

    @GetMapping("/listQuittances")
    public List<Map<String, Object>> ListQuittances(@RequestParam String CIN) {
        return quittanceDAODAO.ListQuittanceByID(CIN);
    }
}
