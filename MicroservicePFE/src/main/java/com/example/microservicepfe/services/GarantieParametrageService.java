package com.example.microservicepfe.services;

import com.example.microservicepfe.models.GarantieParametrage;

import java.util.List;


public interface GarantieParametrageService {
    List<GarantieParametrage> getGarantiesByTypeOccupantAndPack(Long typeOccupantId, Long packId);
}