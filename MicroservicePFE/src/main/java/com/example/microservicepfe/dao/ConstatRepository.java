package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.Constat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstatRepository extends JpaRepository<Constat, Long> {
}
