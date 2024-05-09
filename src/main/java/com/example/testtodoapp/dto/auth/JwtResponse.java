package com.example.testtodoapp.dto.auth;

public class JwtResponse {
    private String token;


    public JwtResponse() {

    }

    public JwtResponse(String token) {
        this.token = token;

    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }


}
