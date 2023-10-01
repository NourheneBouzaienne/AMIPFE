package com.example.microservicepfe.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FcmTokenRequest {
    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
