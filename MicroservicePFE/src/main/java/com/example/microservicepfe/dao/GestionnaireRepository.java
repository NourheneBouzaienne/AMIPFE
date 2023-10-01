package com.example.microservicepfe.dao;


import com.example.microservicepfe.models.Gestionnaire;
import com.example.microservicepfe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Long> {

    Optional<Gestionnaire> findByUsername(String username);

}
