package com.example.microservicepfe.payload.request;

import java.util.Set;



public class SignupRequest {

    private String username;
    private String name;

    private String email;
    private String adresse;
    private String typeIDNT;
    private String typePers;
    private String password;
    private String numTel;
    private boolean enabled;

    private boolean isAuthentificated;

    public boolean isAuthentificated() {
        return isAuthentificated;
    }

    public void setAuthentificated(boolean authentificated) {
        isAuthentificated = authentificated;
    }

    private String activationCode;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    private Set<String> role;

    public SignupRequest(String activationCode,boolean enabled,boolean isAuthentificated,String name,String username, String email, String adresse, String typeIDNT, String typePers, Set<String> role,String numTel,String password) {
        this.activationCode = activationCode;
        this.enabled = enabled;
        this.isAuthentificated =isAuthentificated;
        this.username = username;
        this.name = name;
        this.email = email;
        this.adresse = adresse;
        this.typeIDNT = typeIDNT;
        this.typePers = typePers;
        this.role = role;
        this.password = password;
        this.numTel= numTel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setTypePers(String typePers) {
        this.typePers = typePers;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}