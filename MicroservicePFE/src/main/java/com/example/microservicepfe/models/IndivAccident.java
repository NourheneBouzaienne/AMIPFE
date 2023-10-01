package com.example.microservicepfe.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class IndivAccident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private String dateNaissance;
    private String profession;
    private Integer capitalDeces;
    private String incapacitePermanente;
    private String indemniteJournaliere;
    private String franchiseJournaliere;
    private String fraisMedicauxRembourses;
    private String plafondFraisMedicaux;
    private String limiteIndemnite;
    private double montantIndemnit;

    private double montantDeces;
    private double montantIncapacite;
    private double  montantFraisMedicaux;
    private double  montantIndemniteJournaliere;
    private double sommePrimes;

    public IndivAccident(String dateNaissance, String profession, Integer capitalDeces, String incapacitePermanente, String indemniteJournaliere, String franchiseJournaliere, String fraisMedicauxRembourses, String plafondFraisMedicaux, String limiteIndemnite, double montantIndemnit, double montantDeces, double montantIncapacite, double montantFraisMedicaux, double montantIndemniteJournaliere, double sommePrimes) {
        this.dateNaissance = dateNaissance;
        this.profession = profession;
        this.capitalDeces = capitalDeces;
        this.incapacitePermanente = incapacitePermanente;
        this.indemniteJournaliere = indemniteJournaliere;
        this.franchiseJournaliere = franchiseJournaliere;
        this.fraisMedicauxRembourses = fraisMedicauxRembourses;
        this.plafondFraisMedicaux = plafondFraisMedicaux;
        this.limiteIndemnite = limiteIndemnite;
        this.montantIndemnit = montantIndemnit;
        this.montantDeces = montantDeces;
        this.montantIncapacite = montantIncapacite;
        this.montantFraisMedicaux = montantFraisMedicaux;
        this.montantIndemniteJournaliere = montantIndemniteJournaliere;
        this.sommePrimes = sommePrimes;
    }

    public IndivAccident() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getCapitalDeces() {
        return capitalDeces;
    }

    public void setCapitalDeces(Integer capitalDeces) {
        this.capitalDeces = capitalDeces;
    }

    public String getIncapacitePermanente() {
        return incapacitePermanente;
    }

    public void setIncapacitePermanente(String incapacitePermanente) {
        this.incapacitePermanente = incapacitePermanente;
    }

    public String getIndemniteJournaliere() {
        return indemniteJournaliere;
    }

    public void setIndemniteJournaliere(String indemniteJournaliere) {
        this.indemniteJournaliere = indemniteJournaliere;
    }

    public String getFranchiseJournaliere() {
        return franchiseJournaliere;
    }

    public void setFranchiseJournaliere(String franchiseJournaliere) {
        this.franchiseJournaliere = franchiseJournaliere;
    }

    public String getFraisMedicauxRembourses() {
        return fraisMedicauxRembourses;
    }

    public void setFraisMedicauxRembourses(String fraisMedicauxRembourses) {
        this.fraisMedicauxRembourses = fraisMedicauxRembourses;
    }

    public String getPlafondFraisMedicaux() {
        return plafondFraisMedicaux;
    }

    public void setPlafondFraisMedicaux(String plafondFraisMedicaux) {
        this.plafondFraisMedicaux = plafondFraisMedicaux;
    }

    public String getLimiteIndemnite() {
        return limiteIndemnite;
    }

    public void setLimiteIndemnite(String limiteIndemnite) {
        this.limiteIndemnite = limiteIndemnite;
    }

    public double getMontantIndemnit() {
        return montantIndemnit;
    }

    public void setMontantIndemnit(double montantIndemnit) {
        this.montantIndemnit = montantIndemnit;
    }

    public double getMontantDeces() {
        return montantDeces;
    }

    public void setMontantDeces(double montantDeces) {
        this.montantDeces = montantDeces;
    }

    public double getMontantIncapacite() {
        return montantIncapacite;
    }

    public void setMontantIncapacite(double montantIncapacite) {
        this.montantIncapacite = montantIncapacite;
    }

    public double getMontantFraisMedicaux() {
        return montantFraisMedicaux;
    }

    public void setMontantFraisMedicaux(double montantFraisMedicaux) {
        this.montantFraisMedicaux = montantFraisMedicaux;
    }

    public double getMontantIndemniteJournaliere() {
        return montantIndemniteJournaliere;
    }

    public void setMontantIndemniteJournaliere(double montantIndemniteJournaliere) {
        this.montantIndemniteJournaliere = montantIndemniteJournaliere;
    }

    public double getSommePrimes() {
        return sommePrimes;
    }

    public void setSommePrimes(double sommePrimes) {
        this.sommePrimes = sommePrimes;
    }
}
