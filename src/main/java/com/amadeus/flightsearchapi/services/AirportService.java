package com.amadeus.flightsearchapi.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.amadeus.flightsearchapi.models.Airport;
import com.amadeus.flightsearchapi.repositories.IAirportRepository;

@Service
public class AirportService {

    private final IAirportRepository airportRepository;

    AirportService(IAirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAll(){
        return airportRepository.findAll();
    }

    public boolean existsByCity(String city){
        return airportRepository.existsByCity(city);
    }

}
