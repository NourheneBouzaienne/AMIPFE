package com.example.microservicepfe.models;


import javax.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {


}
