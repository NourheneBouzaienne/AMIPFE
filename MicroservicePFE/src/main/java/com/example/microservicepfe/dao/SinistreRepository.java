package com.example.microservicepfe.dao;


import com.example.microservicepfe.models.Sinistre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinistreRepository extends JpaRepository<Sinistre, Long> {
}
