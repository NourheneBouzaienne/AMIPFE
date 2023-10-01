package com.example.microservicepfe.models;

import javax.persistence.*;


@Entity
public class DemandeGarantie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;
    private String garantie;
    private String numCNT;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public DemandeGarantie(Long id, String garantie, String numCNT, User user) {
        this.id = id;
        this.garantie = garantie;
        this.numCNT = numCNT;
        this.user = user;
    }

    public DemandeGarantie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGarantie() {
        return garantie;
    }

    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }

    public String getNumCNT() {
        return numCNT;
    }

    public void setNumCNT(String numCNT) {
        this.numCNT = numCNT;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
