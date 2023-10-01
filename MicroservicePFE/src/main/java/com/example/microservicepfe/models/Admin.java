package com.example.microservicepfe.models;


import javax.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {


    public Admin(String activationCode, boolean enabled, String name, String username, String email, String numTel, String password) {
        super(activationCode, enabled, name, username, email, numTel, password);
    }

    public Admin() {
    }
}
