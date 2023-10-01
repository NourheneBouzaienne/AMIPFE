package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.Garantie;
import com.example.microservicepfe.models.GarantieParametrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarantieParametrageRepository extends JpaRepository<GarantieParametrage, Long> {
    List<GarantieParametrage> findByTypeOccupantIdAndPackId(Long typeOccupantId, Long packId);
}
