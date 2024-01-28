package com.amadeus.flightsearchapi.application.flight_information_collector;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.amadeus.flightsearchapi.application.models.Airport;
import com.amadeus.flightsearchapi.application.models.Flight;
import com.amadeus.flightsearchapi.application.services.AirportService;

@Component("offlineCollector")
public class FlightInformationCollectorImpl implements IFlightInformationCollector {

    private final AirportService airportService;
    private final long FLIGHT_GENERATION_COUNT = 200L;

    public FlightInformationCollectorImpl(AirportService airportService){
        this.airportService = airportService;
    }

    @Override
    public List<Flight> requestFlights() {
        List<Flight> flights = new LinkedList<>();
        List<Airport> airports = airportService.getAll();

        if(airports.isEmpty()){
            throw new RuntimeException("No airport given to generate flight");
        }

        for (int i = 0; i < FLIGHT_GENERATION_COUNT; i++) {

            Random random = new Random();

            int departureAirportIndex = random.nextInt(airports.size());
            int arrivalAirportIndex = -1;

            do {
                arrivalAirportIndex = random.nextInt(airports.size());
            } while (departureAirportIndex == arrivalAirportIndex);

            // now <---> now + 10 days
            Instant departureTime = Instant.now()
                    .plus(random.nextInt(864_000), ChronoUnit.SECONDS);

            // departureTime <---> departureTime + 3 days
            Instant arrivalTime = Instant.ofEpochMilli(departureTime.toEpochMilli())
                    .plus(random.nextInt(259_200), ChronoUnit.SECONDS);

            long price = arrivalTime.minus(departureTime.toEpochMilli(), ChronoUnit.MILLIS).getEpochSecond() / 2L;

            Flight flight = Flight.builder()
                    .departureAirport(airports.get(departureAirportIndex))
                    .arrivalAirport(airports.get(arrivalAirportIndex))
                    .departureDate(Date.from(departureTime))
                    .arrivalDate(Date.from(arrivalTime))
                    .price(price)
                    .build();

            flights.add(flight);
        }

        return flights;
    }
    
}
