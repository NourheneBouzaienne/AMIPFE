package com.example.microservicepfe.models;


import javax.persistence.*;


@Entity
@DiscriminatorValue("GESTIONNAIRE")
public class Gestionnaire extends User {


}
