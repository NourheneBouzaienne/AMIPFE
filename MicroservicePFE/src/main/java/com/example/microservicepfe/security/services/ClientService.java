package com.example.microservicepfe.security.services;

import com.example.microservicepfe.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client createClient(Client client);
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);
    Client updateClient(Client client);
    void deleteClientById(Long id);
}
