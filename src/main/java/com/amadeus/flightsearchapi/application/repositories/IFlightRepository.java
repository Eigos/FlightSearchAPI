package com.amadeus.flightsearchapi.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.amadeus.flightsearchapi.application.models.Flight;

import java.util.UUID;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, UUID>, JpaSpecificationExecutor<Flight> {
    
}