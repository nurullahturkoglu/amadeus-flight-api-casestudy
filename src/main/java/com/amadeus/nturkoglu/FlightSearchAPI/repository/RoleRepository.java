package com.amadeus.nturkoglu.FlightSearchAPI.repository;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

}