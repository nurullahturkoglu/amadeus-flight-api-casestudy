package com.amadeus.nturkoglu.FlightSearchAPI.service;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Airport;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AirportService {
    ResponseEntity<List<Airport>> getAllAirport();

    ResponseEntity<Airport> getAirportById(int id);

    ResponseEntity<List<Airport>> getAirportByCity(String city);

    ResponseEntity<Airport> createAirport(Airport airport);

    ResponseEntity<Airport> updateAirport(int id, Airport airport);

    ResponseEntity<Boolean> deleteAirport(int id);
}
