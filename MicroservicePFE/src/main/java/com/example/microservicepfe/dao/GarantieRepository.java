package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.Garantie;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GarantieRepository extends JpaRepository<Garantie, Long> {
    //@Query("SELECT g FROM Garantie g WHERE g.pack.type = :packType AND g.typeOccupant.nom = :occupantType")
    //List<Garantie> findByPackTypeAndOccupantType(@Param("packType") String packType, @Param("occupantType") String occupantType);
}
