package com.example.microservicepfe.services;

import com.example.microservicepfe.models.*;

import java.util.List;
import java.util.Optional;

public interface DevisService {

    Devis createMultiRisqueHabitationDevis(MultiRisqueHabitation multiRisqueHabitation);
    //List<Garantie> getGarantiesByOccupantTypeAndPackType(String occupantType, String packType);

    List<Devis> getAllDevis();
    Optional<Devis> getDevisById(Long id);
}
