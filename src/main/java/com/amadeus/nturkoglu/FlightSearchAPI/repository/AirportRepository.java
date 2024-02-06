package com.amadeus.nturkoglu.FlightSearchAPI.repository;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {

    List<Airport> findByCity(String city);

    Airport getAirportByCity(String City);

}