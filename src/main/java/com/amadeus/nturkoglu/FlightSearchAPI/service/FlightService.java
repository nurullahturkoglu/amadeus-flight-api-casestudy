package com.amadeus.nturkoglu.FlightSearchAPI.service;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Flight;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    ResponseEntity<List<Flight>> getAllFlight();

    ResponseEntity<Flight> getFlightById(int id);

    ResponseEntity<List<Flight>> searchFlight(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime);

    ResponseEntity<Flight> createFlight(Flight flight);

    ResponseEntity<Flight> updateFlight(int id, Flight flight);

    ResponseEntity<Boolean> deleteFlight(int id);

}