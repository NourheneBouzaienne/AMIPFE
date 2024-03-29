package com.example.microservicepfe.dao;

import com.example.microservicepfe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByEmail(String email);
    User findUserByUsername(String username);

    User findByActivationCode(String activationCode);






    Boolean existsByUsername(String username);


    Boolean existsByEmail(String email);
}
