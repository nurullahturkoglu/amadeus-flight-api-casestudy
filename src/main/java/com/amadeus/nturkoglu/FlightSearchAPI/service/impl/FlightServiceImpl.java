package com.amadeus.nturkoglu.FlightSearchAPI.service.impl;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Flight;
import com.amadeus.nturkoglu.FlightSearchAPI.repository.AirportRepository;
import com.amadeus.nturkoglu.FlightSearchAPI.repository.FlightRepository;
import com.amadeus.nturkoglu.FlightSearchAPI.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirportRepository airportRepository;

    @Override
    public ResponseEntity<List<Flight>> getAllFlight() {
        List<Flight> flights = flightRepository.findAll();
        return ResponseEntity.ok(flights);
    }
    @Override
    public ResponseEntity<Flight> getFlightById(int id) {
        Flight flight = flightRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Flight not found with id " + id));

        return ResponseEntity.ok(flight);
    }

    @Override
    public ResponseEntity<List<Flight>> searchFlight(String departureCity, String arrivalCity, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        int departureAirportId = airportRepository.getAirportByCity(departureCity).getAirportId();
        int arrivalAirportId = airportRepository.getAirportByCity(arrivalCity).getAirportId();

        if (arrivalDateTime == null) {
            return ResponseEntity.ok(findOneWayFlights(departureAirportId, arrivalAirportId, departureDateTime));
        } else {
            return ResponseEntity.ok(findRoundTripFlights(departureAirportId, arrivalAirportId, departureDateTime, arrivalDateTime));
        }
    }

    private List<Flight> findOneWayFlights(int departureAirportId, int arrivalAirportId, LocalDateTime departureDateTime) {
        return flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
                departureAirportId, arrivalAirportId, departureDateTime);
    }

    private List<Flight> findRoundTripFlights(int departureAirportId, int arrivalAirportId, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        List<Flight> outboundFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
                departureAirportId, arrivalAirportId, departureDateTime);
        List<Flight> returnFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
                arrivalAirportId, departureAirportId, arrivalDateTime);

        return Stream.concat(outboundFlights.stream(), returnFlights.stream())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Flight> createFlight(Flight flight) {
        return ResponseEntity.ok(flightRepository.save(flight));
    }

    @Override
    public ResponseEntity<Flight> updateFlight(int id, Flight flight) {
        flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Flight not found with id " + id));

        flight.setId(id);
        return ResponseEntity.ok(flightRepository.save(flight));
    }

    @Override
    public ResponseEntity<Boolean> deleteFlight(int id) {
        flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Flight not found with id " + id));

        flightRepository.deleteById(id);
        return ResponseEntity.ok(true);
    }
}
