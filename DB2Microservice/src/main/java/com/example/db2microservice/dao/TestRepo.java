package com.example.db2microservice.dao;

import com.example.db2microservice.entities.Testapp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo  extends JpaRepository<Testapp, Long> {
}