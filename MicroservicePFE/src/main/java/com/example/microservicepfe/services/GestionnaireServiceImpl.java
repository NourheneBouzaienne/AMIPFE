package com.example.microservicepfe.services;

import com.example.microservicepfe.dao.ClientRepository;
import com.example.microservicepfe.dao.GestionnaireRepository;
import com.example.microservicepfe.models.Client;
import com.example.microservicepfe.models.Gestionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GestionnaireServiceImpl implements GestionnaireService {

    private final GestionnaireRepository gestionnaireRepository;


    @Autowired
    public GestionnaireServiceImpl(GestionnaireRepository gestionnaireRepository) {
        this.gestionnaireRepository = gestionnaireRepository;
    }

    @Override
    public Gestionnaire createGestionnaire(Gestionnaire gestionnaire) {
        return gestionnaireRepository.save(gestionnaire);
    }

    @Override
    public List<Gestionnaire> getAllGestionnaires() {
        return gestionnaireRepository.findAll();
    }

    @Override
    public Optional<Gestionnaire> getGestionnaireById(Long id) {
        return gestionnaireRepository.findById(id);
    }

    @Override public Gestionnaire updateGestionnaire(Gestionnaire gestionnaire) {
        return gestionnaireRepository.save(gestionnaire);
    }

    @Override
    public void deleteGestionnaireById(Long id) {
        gestionnaireRepository.deleteById(id);
    }
}
