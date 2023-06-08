package com.example.microservicepfe.models;


import javax.persistence.*;


@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {
    public Client() {
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTypeIDNT() {
        return typeIDNT;
    }

    public void setTypeIDNT(String typeIDNT) {
        this.typeIDNT = typeIDNT;
    }

    public String getTypePers() {
        return typePers;
    }

    public Client(String adresse, String typeIDNT, String typePers) {
        this.adresse = adresse;
        this.typeIDNT = typeIDNT;
        this.typePers = typePers;
    }

    public void setTypePers(String typePers) {
        this.typePers = typePers;
    }

    private String adresse;
    private String typeIDNT;
    private String typePers;
    private String numTel;








    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Client(String activationCode, boolean enabled,boolean isAuthentificated, String name, String username, String email, String adresse, String typeIDNT, String typePers, String numTel, String password ) {
        super(activationCode, enabled, name, username, email, password,isAuthentificated);
        this.adresse = adresse;
        this.typeIDNT = typeIDNT;
        this.typePers = typePers;
        this.numTel = numTel;

    }


}
