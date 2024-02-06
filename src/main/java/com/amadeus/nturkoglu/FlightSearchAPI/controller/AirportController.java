package com.amadeus.nturkoglu.FlightSearchAPI.controller;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Airport;
import com.amadeus.nturkoglu.FlightSearchAPI.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @Operation(summary = "Get all airport information.")
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        return airportService.getAllAirport();
    }

    @Operation(summary = "Get an airport information by airport id.")
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable int id) {
        return airportService.getAirportById(id);
    }

    @Operation(summary = "Add new airport to database.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        return airportService.createAirport(airport);
    }

    @Operation(summary = "Update an existing airport from database.")
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable int id, @RequestBody Airport airport) {
        return airportService.updateAirport(id, airport);
    }

    @Operation(summary = "Delete an existing airport from database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAirport(@PathVariable int id) {
        return airportService.deleteAirport(id);
    }
}
