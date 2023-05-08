package com.example.microservicepfe.dao;


import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DemandeRepository  extends JpaRepository<Demande, Long> {
    List<Demande> findByObjectContaining(String object);

    List<Demande> findByUser(User user);

}
