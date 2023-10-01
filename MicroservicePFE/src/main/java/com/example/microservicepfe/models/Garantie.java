package com.example.microservicepfe.models;

import javax.persistence.*;

@Entity
public class Garantie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categorie;
    private String nom;

    @ManyToOne(fetch = FetchType.EAGER)

    private Pack pack;



    @ManyToOne(fetch = FetchType.EAGER)
    private TypeOccupant typeOccupant;

    // Getters and setters

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

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public TypeOccupant getTypeOccupant() {
        return typeOccupant;
    }

    public void setTypeOccupant(TypeOccupant typeOccupant) {
        this.typeOccupant = typeOccupant;
    }

    public Garantie(Long id, String categorie, String nom, Pack pack, TypeOccupant typeOccupant) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.pack = pack;
        this.typeOccupant = typeOccupant;
    }

    public Garantie() {
    }
}