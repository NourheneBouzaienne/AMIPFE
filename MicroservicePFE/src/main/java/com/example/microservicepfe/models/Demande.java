package com.example.microservicepfe.models;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "reclamations")

public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String object;
    private String description;

    private String categ;

    private String reponse;

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getEtat() {
        return etat;
    }

    public Demande(Long id, String object, String description, String etat, Date dateCreation,String categ, User user, String reponse) {
        this.id = id;
        this.object = object;
        this.description = description;
        this.etat = etat;
        this.dateCreation = dateCreation;
        this.categ = categ;
        this.user = user;
        this.reponse = reponse;

    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    private String etat;


    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date dateCreation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_id", nullable = false)
    private User user;

    public Demande(String object, String description, Date dateCreation) {
        this.object = object;
        this.description = description;
        this.dateCreation = dateCreation;

    }

    public Demande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
