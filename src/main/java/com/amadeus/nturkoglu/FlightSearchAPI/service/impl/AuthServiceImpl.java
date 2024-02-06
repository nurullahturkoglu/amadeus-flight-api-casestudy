package com.amadeus.nturkoglu.FlightSearchAPI.service.impl;

import com.amadeus.nturkoglu.FlightSearchAPI.dto.AuthDto;
import com.amadeus.nturkoglu.FlightSearchAPI.entity.Role;
import com.amadeus.nturkoglu.FlightSearchAPI.dto.LoginDto;
import com.amadeus.nturkoglu.FlightSearchAPI.dto.RegisterDto;
import com.amadeus.nturkoglu.FlightSearchAPI.entity.UserEntity;
import com.amadeus.nturkoglu.FlightSearchAPI.repository.AuthRepository;
import com.amadeus.nturkoglu.FlightSearchAPI.repository.RoleRepository;
import com.amadeus.nturkoglu.FlightSearchAPI.service.AuthService;
import com.amadeus.nturkoglu.FlightSearchAPI.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseEntity<String> registerUser(RegisterDto registerDto) {
        if(authRepository.existsByUsername(registerDto.getUsername())){
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role userRole = roleRepository
                .findByName("USER")
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Error: Role is not found."));


        user.setRoles(Collections.singletonList(userRole));
        authRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Override
    public ResponseEntity<AuthDto> loginUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(new AuthDto(token));
    }
}
