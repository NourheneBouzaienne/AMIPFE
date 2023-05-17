package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.Client;
import com.example.microservicepfe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {

   Client findClientByUsername(String username);

}
