package com.amadeus.flightsearchapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amadeus.flightsearchapi.models.Airport;
import java.util.UUID;

@Repository
public interface IAirportRepository extends JpaRepository<Airport, UUID> {
    
}