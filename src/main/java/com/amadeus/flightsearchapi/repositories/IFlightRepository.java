package com.amadeus.flightsearchapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amadeus.flightsearchapi.models.Flight;

import java.util.UUID;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, UUID> {
    
}