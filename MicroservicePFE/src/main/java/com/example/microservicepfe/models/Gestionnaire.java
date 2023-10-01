package com.example.microservicepfe.models;


import javax.persistence.*;


@Entity
@DiscriminatorValue("GESTIONNAIRE")
public class Gestionnaire extends User {


    public Gestionnaire(String activationCode, boolean enabled, String name, String username, String email, String numTel, String password) {
        super(activationCode, enabled, name, username, email, numTel, password);
    }

    public Gestionnaire() {
    }
}