package com.amadeus.flightsearchapi.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.flightsearchapi.dto.FlightInformation;
import com.amadeus.flightsearchapi.exceptions.FlightNotFoundException;
import com.amadeus.flightsearchapi.models.Flight;
import com.amadeus.flightsearchapi.services.FlightService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/admin/flight")
public class FlightAdminController {
  
    private final FlightService flightService;

    FlightAdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getFlights(){
        return ResponseEntity.ok(flightService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlight(@PathVariable("id") @NotNull UUID flightID){
        return ResponseEntity.ok(flightService.getByID(flightID).orElseThrow(FlightNotFoundException::new));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFlight(@PathVariable("id") @NotNull UUID flightID){
        flightService.delete(flightID);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<Void> appendFlight(@Valid @RequestBody FlightInformation flightInformation){
        flightService.save(flightInformation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
