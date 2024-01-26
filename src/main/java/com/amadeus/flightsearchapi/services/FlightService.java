package com.amadeus.flightsearchapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amadeus.flightsearchapi.models.Flight;
import com.amadeus.flightsearchapi.repositories.IFlightRepository;

@Service
public class FlightService {

    private final IFlightRepository flightRepository;
    private final AirportService airportService;

    FlightService(IFlightRepository flightRepository, AirportService airportService) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
    }

    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

}
