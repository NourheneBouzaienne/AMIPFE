package com.example.microservicepfe.models;


import javax.persistence.*;


@Entity
@Table(name = "notification")
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;
    private String contenu;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification(Long id, String contenu, User user) {
        this.id = id;
        this.contenu = contenu;
        this.user = user;
    }

    public Notification() {
    }
}
