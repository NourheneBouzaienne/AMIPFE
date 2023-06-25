package com.example.microservicepfe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Sinistre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Temporal(TemporalType.DATE)
    private Date date;

    private String numCnt;

    private String description;

    private String referenceCode;

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    private double latitude;
    private double longitude;
    private String lieu;


    public Sinistre() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumCnt() {
        return numCnt;
    }

    public void setNumCnt(String numCnt) {
        this.numCnt = numCnt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.lieu = lieu;
    }


    @JsonIgnoreProperties("sinistre")
    @OneToMany(mappedBy = "sinistre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhotoSinistre> photos;

    @JsonIgnoreProperties("sinistre")
    @OneToMany(mappedBy = "sinistre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Constat> constat;

    public List<Constat> getConstat() {
        return constat;
    }

    public void setConstat(List<Constat> constat) {
        this.constat = constat;
    }

    public List<PhotoSinistre> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoSinistre> photos) {
        this.photos = photos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}

