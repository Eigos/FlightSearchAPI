package com.amadeus.flightsearchapi.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.amadeus.flightsearchapi.DateTimeUtils;
import com.amadeus.flightsearchapi.dto.FlightInformation;
import com.amadeus.flightsearchapi.models.Flight;
import com.amadeus.flightsearchapi.repositories.IFlightRepository;

import jakarta.persistence.criteria.Predicate;

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

    public List<Flight> findFlights(FlightInformation flightSearchInformation) {

        return flightRepository.findAll((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

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

}
