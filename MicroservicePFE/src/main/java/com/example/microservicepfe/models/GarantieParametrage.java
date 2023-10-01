package com.example.microservicepfe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class GarantieParametrage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categorie;
    private String nom;

    @JsonIgnore
    @ManyToOne
    private Pack pack;

    @ManyToOne
    private TypeOccupant typeOccupant;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeOccupant getTypeOccupant() {
        return typeOccupant;
    }

    public void setTypeOccupant(TypeOccupant typeOccupant) {
        this.typeOccupant = typeOccupant;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public GarantieParametrage(Long id, String categorie, String nom, TypeOccupant typeOccupant, Pack pack) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.typeOccupant = typeOccupant;
        this.pack = pack;
    }

    public GarantieParametrage() {
    }
}
