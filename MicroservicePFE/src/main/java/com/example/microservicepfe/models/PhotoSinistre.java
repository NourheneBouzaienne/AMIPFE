package com.example.microservicepfe.models;


import javax.persistence.*;


@Entity
public class PhotoSinistre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    //private String desc;


    @ManyToOne
    @JoinColumn(name = "sinistre_id")
    private Sinistre sinistre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public Sinistre getSinistre() {
        return sinistre;
    }

    public void setSinistre(Sinistre sinistre) {
        this.sinistre = sinistre;
    }

    public PhotoSinistre(String url, Sinistre sinistre) {
        this.url = url;
        //this.desc = desc;
        this.sinistre = sinistre;
    }

    public PhotoSinistre() {
    }


}



