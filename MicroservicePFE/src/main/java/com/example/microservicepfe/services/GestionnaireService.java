package com.example.microservicepfe.services;

import com.example.microservicepfe.models.Client;
import com.example.microservicepfe.models.Gestionnaire;

import java.util.List;
import java.util.Optional;

public interface GestionnaireService {

    Gestionnaire createGestionnaire(Gestionnaire gestionnaire);
    List<Gestionnaire> getAllGestionnaires();
    Optional<Gestionnaire> getGestionnaireById(Long id);
    Gestionnaire updateGestionnaire(Gestionnaire gestionnaire);
    void deleteGestionnaireById(Long id);
}
