package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.Devis;
import com.example.microservicepfe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DevisRepository extends JpaRepository<Devis, Long> {

    List<Devis> findByUser(User user);


}
