package com.example.microservicepfe.models;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("MultiRisqueHabitation")
public class MultiRisqueHabitation  extends Devis {



    @JsonProperty("montantImmobilier")
    private double montantImmobilier;
    @JsonProperty("catégorie")
    private String categ;

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    @JsonProperty("montantMobilier")
    private double montantMobilier;

    @JsonProperty("nombrePieces")
    private int nombrePieces;
    @ManyToOne
    @JoinColumn(name = "pack_id")
    @JsonProperty("pack")
    private Pack pack;
    @ManyToOne
    @JoinColumn(name = "type_occupant_id")
    @JsonProperty("typeOccupant")
    private TypeOccupant typeOccupant;

    public TypeOccupant getTypeOccupant() {
        return typeOccupant;
    }

    public void setTypeOccupant(TypeOccupant typeOccupant) {
        this.typeOccupant = typeOccupant;
    }

    public int getNombrePieces() {
        return nombrePieces;
    }

    public void setNombrePieces(int nombrePieces) {
        this.nombrePieces = nombrePieces;
    }

    public double getMontantMobilier() {
        return montantMobilier;
    }

    public void setMontantMobilier(double montantMobilier) {
        this.montantMobilier = montantMobilier;
    }

    public double getMontantImmobilier() {
        return montantImmobilier;
    }

    public void setMontantImmobilier(double montantImmobilier) {
        this.montantImmobilier = montantImmobilier;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }



    public MultiRisqueHabitation(@JsonProperty("montantImmobilier") double montantImmobilier,
                                 @JsonProperty("montantMobilier") double montantMobilier,
                                 @JsonProperty("nombrePieces") int nombrePieces,
                                 @JsonProperty("pack") Pack pack,
                                 @JsonProperty("typeOccupant") TypeOccupant typeOccupant,
                                 @JsonProperty("catégorie") String categ
) {

        this.montantImmobilier = montantImmobilier;
        this.montantMobilier = montantMobilier;
        this.nombrePieces = nombrePieces;
        this.pack = pack;
        this.typeOccupant = typeOccupant;
        this.categ= categ;
    }

    public MultiRisqueHabitation() {
    }
}
