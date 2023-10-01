package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.Notification;

import com.example.microservicepfe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findByUser(User user);
}
