package com.example.microservicepfe.utils;

import java.util.Random;

public class PasswordGenerator {


    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[{]}|;:',<.>/?";

    // Longueur du mot de passe généré
    private static final int PASSWORD_LENGTH = 12;

    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}