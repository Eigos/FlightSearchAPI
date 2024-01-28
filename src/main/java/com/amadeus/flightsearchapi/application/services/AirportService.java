package com.amadeus.flightsearchapi.application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amadeus.flightsearchapi.application.models.Airport;
import com.amadeus.flightsearchapi.application.repositories.IAirportRepository;
import com.amadeus.flightsearchapi.auth.service.ICrudService;

@Service
public class AirportService implements ICrudService<Airport> {

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

    @Override
    public Optional<Airport> getByID(UUID id) {
        return airportRepository.findById(id);
    }

    @Override
    public Airport create(Airport entity) {
        return airportRepository.save(entity);
    }

    @Override
    public Airport update(Airport entity) throws Exception {
        return airportRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        airportRepository.deleteById(id);
    }

    
}
