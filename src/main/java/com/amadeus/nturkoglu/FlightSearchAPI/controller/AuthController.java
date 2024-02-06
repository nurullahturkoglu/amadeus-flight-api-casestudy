package com.amadeus.nturkoglu.FlightSearchAPI.controller;

import com.amadeus.nturkoglu.FlightSearchAPI.dto.AuthDto;
import com.amadeus.nturkoglu.FlightSearchAPI.dto.LoginDto;
import com.amadeus.nturkoglu.FlightSearchAPI.dto.RegisterDto;
import com.amadeus.nturkoglu.FlightSearchAPI.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return authService.registerUser(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto){
        return authService.loginUser(loginDto);
    }
}
