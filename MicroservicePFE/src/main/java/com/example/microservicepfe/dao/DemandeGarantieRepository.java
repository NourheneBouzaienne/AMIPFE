package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.DemandeGarantie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DemandeGarantieRepository extends JpaRepository<DemandeGarantie, Long> {
}
