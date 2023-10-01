package com.example.microservicepfe.services;

import com.example.microservicepfe.dao.DevisRepository;
import com.example.microservicepfe.dao.GarantieRepository;
import com.example.microservicepfe.dao.PackRepository;
import com.example.microservicepfe.dao.TypeOccupantRespository;
import com.example.microservicepfe.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DevisServiceImpl implements DevisService {
    @Autowired
     DevisRepository devisRepository;
    @Autowired
    GarantieRepository garantieRepository;

    @Autowired
    PackRepository packRepository;

    @Autowired
    TypeOccupantRespository typeOccupantRepository;


    /*@Override
    public Devis createMultiRisqueHabitationDevis(Devis devis) {
        // Ajoutez ici des validations, des traitements spécifiques, etc.
        return devisRepository.save(devis);
    }*/
    @Override
    public Devis createMultiRisqueHabitationDevis(MultiRisqueHabitation multiRisqueHabitation) {

        Long packId = multiRisqueHabitation.getPack().getId();
        Long typeId = multiRisqueHabitation.getTypeOccupant().getId();
        double montantImmobilier = multiRisqueHabitation.getMontantImmobilier();
        double montantMobilier = multiRisqueHabitation.getMontantMobilier();
        Pack pack = packRepository.findById(packId).orElse(null);
        TypeOccupant typeOccupant = typeOccupantRepository.findById(typeId).orElse(null);

        multiRisqueHabitation.setPack(pack);
        multiRisqueHabitation.setTypeOccupant(typeOccupant);
        multiRisqueHabitation.setMontantImmobilier(montantImmobilier);
        multiRisqueHabitation.setMontantMobilier(montantMobilier);


        // À ce stade, l'objet MultiRisqueHabitation devrait contenir toutes les informations nécessaires pour créer un devis complet
        // Vous pouvez maintenant procéder à la création du devis dans la base de données

        return devisRepository.save(multiRisqueHabitation);
    }


   /* @Override
    public List<Garantie> getGarantiesByOccupantTypeAndPackType(String occupantType, String packType) {
        // Effectuez une requête à la base de données pour récupérer les garanties en fonction des paramètres occupantType et packType
        // Vous pouvez utiliser les méthodes du repository ou définir une requête personnalisée
        return garantieRepository.findByPackTypeAndOccupantType(packType, occupantType);
    }*/

    @Override
    public List<Devis> getAllDevis () {
        return devisRepository.findAll();
    }


    @Override
    public Optional<Devis> getDevisById(Long id) {
        return devisRepository.findById(id);
    }
}
