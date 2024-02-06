package com.amadeus.nturkoglu.FlightSearchAPI.service;

import org.springframework.security.core.Authentication;

public interface JwtService {

    String generateToken(Authentication authentication);

    String getUsernameFromToken(String token);

    boolean validateToken(String token);
}
