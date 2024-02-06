package com.amadeus.nturkoglu.FlightSearchAPI.repository;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);

}