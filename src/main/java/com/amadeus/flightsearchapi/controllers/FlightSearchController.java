package com.amadeus.flightsearchapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.amadeus.flightsearchapi.dto.FlightInformation;
import com.amadeus.flightsearchapi.dto.FlightInformationResponse;
import com.amadeus.flightsearchapi.dto.FlightSearchRequest;
import com.amadeus.flightsearchapi.dto.FlightSearchResponse;
import com.amadeus.flightsearchapi.services.AirportService;
import com.amadeus.flightsearchapi.services.FlightService;

import jakarta.validation.Valid;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightSearchController {

    private final FlightService flightService;
    private final ModelMapper mapper;

    FlightSearchController(FlightService flightService, AirportService airportService, ModelMapper mapper) {
        this.flightService = flightService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<FlightSearchResponse> getAllAvailableFlights(
            @Valid @RequestBody FlightSearchRequest flightSearchRequest) {

        FlightSearchResponse flightSearchResponse = new FlightSearchResponse();

        flightSearchResponse.setFlightDeparture(
                flightService
                        .findFlights(mapper.map(flightSearchRequest, FlightInformation.class))
                        .stream()
                        .map(v -> mapper.map(v, FlightInformationResponse.class))
                        .collect(Collectors.toList()));

        if (flightSearchRequest.getReturnDate() != null)
            flightSearchResponse.setFlightReturn(
                    flightService.findFlights(
                            FlightInformation.builder()
                                    .arrivalAirport(flightSearchRequest.getDepartureAirportCity())
                                    .departureAirport(flightSearchRequest.getArrivalAirportCity())
                                    .departureDate(flightSearchRequest.getReturnDate())
                                    .build())
                    .stream()
                    .map(v -> mapper.map(v, FlightInformationResponse.class))
                    .collect(Collectors.toList()));

        return ResponseEntity.ok(flightSearchResponse);
    }

}
