package com.amadeus.nturkoglu.FlightSearchAPI.service.impl;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Airport;
import com.amadeus.nturkoglu.FlightSearchAPI.repository.AirportRepository;
import com.amadeus.nturkoglu.FlightSearchAPI.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;
    @Override
    public ResponseEntity<List<Airport>> getAllAirport() {
        List<Airport> airports = airportRepository.findAll();
        return ResponseEntity.ok(airports);
    }

    @Override
    public ResponseEntity<Airport> getAirportById(int id) {
        Airport airport = airportRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Airport not found with id " + id));

        return ResponseEntity.ok(airport);
    }

    @Override
    public ResponseEntity<List<Airport>> getAirportByCity(String city) {
        List<Airport> airports = airportRepository.findByCity(city);
        return ResponseEntity.ok(airports);
    }

    @Override
    public ResponseEntity<Airport> createAirport(Airport airport) {
        Airport createdAirport = airportRepository.save(airport);
        return ResponseEntity.ok(createdAirport);
    }

    @Override
    public ResponseEntity<Airport> updateAirport(int id, Airport airport) {
        Airport _airport =  airportRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Airport not found with id " + id));

        _airport.setId(airport.getId());
        airportRepository.save(_airport);
        return ResponseEntity.ok(_airport);
    }

    @Override
    public ResponseEntity<Boolean> deleteAirport(int id) {
        Airport _airport =  airportRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Airport not found with id " + id));

        airportRepository.delete(_airport);
        return ResponseEntity.ok(true);
    }
}
