package com.example.sim.security;

public interface JwtSecurityService {
    void saveJwtToken(String token);
    String getToken();
    void deleteToken();
    boolean isAuth();
}
