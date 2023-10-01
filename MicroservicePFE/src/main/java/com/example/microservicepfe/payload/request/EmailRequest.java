package com.example.microservicepfe.payload.request;

public class EmailRequest {
    private String email;
    private String subject;
    private String content;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String packType;
    private String typeOccupantNom;

    public String getPackType() {
        return packType;
    }

    public void setPackType(String packType) {
        this.packType = packType;
    }

    public String getTypeOccupantNom() {
        return typeOccupantNom;
    }

    public void setTypeOccupantNom(String typeOccupantNom) {
        this.typeOccupantNom = typeOccupantNom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}