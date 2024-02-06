package com.amadeus.nturkoglu.FlightSearchAPI.service;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.UserEntity;
import com.amadeus.nturkoglu.FlightSearchAPI.repository.AuthRepository;
import com.amadeus.nturkoglu.FlightSearchAPI.entity.Role;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CustomUserService implements UserDetailsService {

    private AuthRepository userRepository;

    @Autowired
    public CustomUserService(AuthRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found"));

        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}