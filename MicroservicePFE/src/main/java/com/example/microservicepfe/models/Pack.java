package com.example.microservicepfe.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;


    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL)
    private List<GarantieParametrage> garantiesParametrage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GarantieParametrage> getGarantiesParametrage() {
        return garantiesParametrage;
    }

    public void setGarantiesParametrage(List<GarantieParametrage> garantiesParametrage) {
        this.garantiesParametrage = garantiesParametrage;
    }

    public Pack(Long id, String type, List<GarantieParametrage> garantiesParametrage) {
        this.id = id;
        this.type = type;
        this.garantiesParametrage = garantiesParametrage;
    }

    public Pack() {
    }
}
