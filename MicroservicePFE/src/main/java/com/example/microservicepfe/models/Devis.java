package com.example.microservicepfe.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "devis_type", discriminatorType = DiscriminatorType.STRING)
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Devis(Long id, Date date, User user) {
        this.id = id;
        this.date = date;
        this.user = user;
    }

    public Devis() {
    }
}