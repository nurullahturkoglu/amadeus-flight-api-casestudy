package com.amadeus.nturkoglu.FlightSearchAPI.repository;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartureDateTimeGreaterThanEqual(
            int departureAirportId,
            int arrivalAirportId,
            LocalDateTime departureDateTime
    );

}