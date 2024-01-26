package com.amadeus.flightsearchapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.amadeus.flightsearchapi.dto.FlightInformationResponse;
import com.amadeus.flightsearchapi.services.AirportService;
import com.amadeus.flightsearchapi.services.FlightService;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightSearchController {

    private final FlightService flightService;
    private final AirportService airportService;
    private final ModelMapper mapper;

    FlightSearchController(FlightService flightService, AirportService airportService, ModelMapper mapper) {
        this.flightService = flightService;
        this.airportService = airportService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<FlightInformationResponse>> getAllAvailableFlights() {

        return ResponseEntity.ok(flightService.getAll()
                .stream()
                .map(v -> mapper.map(v, FlightInformationResponse.class))
                .collect(Collectors.toList()));
    }

}
