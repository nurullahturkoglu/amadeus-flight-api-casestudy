package com.amadeus.nturkoglu.FlightSearchAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int airportId;

    private String city;
}
