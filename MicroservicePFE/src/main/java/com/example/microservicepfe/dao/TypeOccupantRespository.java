package com.example.microservicepfe.dao;


import com.example.microservicepfe.models.TypeOccupant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface TypeOccupantRespository extends JpaRepository<TypeOccupant, Long> {
}
