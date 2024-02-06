package com.amadeus.nturkoglu.FlightSearchAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport arrivalAirport;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;

    private double price;

}
