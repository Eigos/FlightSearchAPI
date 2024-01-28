package com.amadeus.flightsearchapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.amadeus.flightsearchapi.DateTimeUtils;
import com.amadeus.flightsearchapi.dto.FlightInformation;
import com.amadeus.flightsearchapi.exceptions.AirportNotFoundException;
import com.amadeus.flightsearchapi.models.Flight;
import com.amadeus.flightsearchapi.repositories.IFlightRepository;

import jakarta.persistence.criteria.Predicate;

@Service
public class FlightService implements ICrudService<Flight> {

    private final IFlightRepository flightRepository;
    private final AirportService airportService;
    private final ModelMapper mapper;

    FlightService(IFlightRepository flightRepository, AirportService airportService, ModelMapper mapper) {
        this.flightRepository = flightRepository;
        this.airportService = airportService;
        this.mapper = mapper;
    }

    public List<Flight> findFlights(FlightInformation flightSearchInformation) {

        return flightRepository.findAll((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(
                    criteriaBuilder.equal(
                            root.join("departureAirport").get("city"),
                            flightSearchInformation.getDepartureAirport().getCity()));

            predicates.add(
                    criteriaBuilder.equal(
                            root.join("arrivalAirport").get("city"),
                            flightSearchInformation.getArrivalAirport().getCity()));

            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("departureDate"),
                            DateTimeUtils.atStartOfDay(flightSearchInformation.getDepartureDate())));

            // Added to find flights in between two days
            predicates.add(
                    criteriaBuilder.lessThan(
                            root.get("departureDate"),
                            DateTimeUtils.atEndOfDay(flightSearchInformation.getDepartureDate())));

            query.orderBy(criteriaBuilder.asc(root.get("departureDate")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public void save(FlightInformation flightInformation) {
        if (!airportService.existsByCity(flightInformation.getDepartureAirport().getCity()))
            throw new AirportNotFoundException();

        if (!airportService.existsByCity(flightInformation.getArrivalAirport().getCity()))
            throw new AirportNotFoundException();

        flightRepository.save(mapper.map(flightInformation, Flight.class));
    }
    
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> getByID(UUID id) {
        return flightRepository.findById(id);
    }

    @Override
    public Flight create(Flight entity) {
        return flightRepository.save(entity);
    }

    @Override
    public Flight update(Flight entity) throws Exception {
        return flightRepository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        flightRepository.deleteById(id);
    }

}
