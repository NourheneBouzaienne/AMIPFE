package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PackRepository extends JpaRepository<Pack, Long> {
}
