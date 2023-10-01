package com.example.microservicepfe.services;


import com.example.microservicepfe.dao.GarantieParametrageRepository;
import com.example.microservicepfe.dao.GarantieRepository;
import com.example.microservicepfe.models.GarantieParametrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarantieParametrageServiceImpl implements GarantieParametrageService {
    @Autowired
    GarantieParametrageRepository garantieParametrageRepository;



    @Override
    public List<GarantieParametrage> getGarantiesByTypeOccupantAndPack(Long typeOccupantId, Long packId) {
        return garantieParametrageRepository.findByTypeOccupantIdAndPackId(typeOccupantId, packId);
    }
}
