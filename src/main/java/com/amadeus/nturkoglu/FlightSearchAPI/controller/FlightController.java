package com.amadeus.nturkoglu.FlightSearchAPI.controller;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Flight;
import com.amadeus.nturkoglu.FlightSearchAPI.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;
    @Operation(summary = "Get all flight information from database.")
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return flightService.getAllFlight();
    }

    @Operation(summary = "Get a flight information by flight id.")
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable int id) {
        return flightService.getFlightById(id);
    }

    @Operation(summary = "Search for flights by departure city name, arrival city name, departure time. Optionally, if the arrival time is given, it will also provide flight information for the return.")
    @PostMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam LocalDateTime departureDateTime,
            @RequestParam(required = false) LocalDateTime arrivalDateTime) {

        return flightService.searchFlight(departureCity, arrivalCity, departureDateTime, arrivalDateTime);
    }

    @Operation(summary = "Add new flight to database.")
    @PostMapping("/create")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);

    }

    @Operation(summary = "Update an existing flight from database.")
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable int id, @RequestBody Flight flight) {
        return flightService.updateFlight(id, flight);
    }

    @Operation(summary = "Delete an existing flight from database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteFlight(@PathVariable int id) {
        return flightService.deleteFlight(id);
    }

}
