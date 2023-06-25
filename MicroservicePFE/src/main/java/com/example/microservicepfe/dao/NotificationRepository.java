package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
