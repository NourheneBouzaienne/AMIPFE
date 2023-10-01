package com.example.microservicepfe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Entity
public class TypeOccupant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;




    @JsonIgnore
    @OneToMany(mappedBy = "typeOccupant", cascade = CascadeType.ALL)
    private List<GarantieParametrage> garantiesParametrage;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<GarantieParametrage> getGarantiesParametrage() {
        return garantiesParametrage;
    }

    public void setGarantiesParametrage(List<GarantieParametrage> garantiesParametrage) {
        this.garantiesParametrage = garantiesParametrage;
    }

    public TypeOccupant(Long id, String nom, List<GarantieParametrage> garantiesParametrage) {
        this.id = id;
        this.nom = nom;
        this.garantiesParametrage = garantiesParametrage;
    }

    public TypeOccupant() {
    }
}
