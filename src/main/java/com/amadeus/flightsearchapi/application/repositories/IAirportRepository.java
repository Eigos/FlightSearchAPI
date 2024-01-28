package com.amadeus.flightsearchapi.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amadeus.flightsearchapi.application.models.Airport;

import java.util.UUID;

@Repository
public interface IAirportRepository extends JpaRepository<Airport, UUID> {
    public boolean existsByCity(String city);
}