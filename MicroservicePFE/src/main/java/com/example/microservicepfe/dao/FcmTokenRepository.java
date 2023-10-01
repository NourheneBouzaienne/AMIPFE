package com.example.microservicepfe.dao;


import com.example.microservicepfe.models.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {
    FcmToken findByUserId(Long userId);


}