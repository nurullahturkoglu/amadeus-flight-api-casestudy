package com.amadeus.nturkoglu.FlightSearchAPI.service;

import org.springframework.security.core.Authentication;

public interface JwtService {

    public String generateToken(Authentication authentication);

    public String getUsernameFromToken(String token);

    public boolean validateToken(String token);
}
