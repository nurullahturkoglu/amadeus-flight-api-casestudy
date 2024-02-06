package com.amadeus.nturkoglu.FlightSearchAPI.cronjob;

import com.amadeus.nturkoglu.FlightSearchAPI.entity.Flight;
import com.amadeus.nturkoglu.FlightSearchAPI.repository.FlightRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CronJob {
    private final FlightRepository flightRepository;
    @Autowired
    public CronJob(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Scheduled(cron = "0 */3 * * * *")
    public void fetchFlightInfoAndSaveToDatabase() {
        // Mock API request
        String mockApiResponse = mockApiRequest();
        Flight flight = processApiResponse(mockApiResponse);
        flightRepository.save(flight);

    }


    private String mockApiRequest(){
        String mockApiResponse = "{"
                + "\"departureAirportId\": 1,"
                + "\"arrivalAirportId\": 2,"
                + "\"arrivalDateTime\": \"2024-02-06T13:30:00\","
                + "\"departureDateTime\": \"2023-02-07T12:30:00\","
                + "\"price\": 4000"
                + "}";

        return mockApiResponse;
    }

    private Flight processApiResponse(String apiResponse) {

        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.readValue(apiResponse, Flight.class);
        } catch (Exception e) {
            return null;
        }
    }

}