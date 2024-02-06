package com.amadeus.nturkoglu.FlightSearchAPI.service;


import com.amadeus.nturkoglu.FlightSearchAPI.dto.AuthDto;
import com.amadeus.nturkoglu.FlightSearchAPI.dto.LoginDto;
import com.amadeus.nturkoglu.FlightSearchAPI.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public ResponseEntity<String> registerUser(RegisterDto registerDto);

    public ResponseEntity<AuthDto> loginUser(LoginDto loginDto);
}