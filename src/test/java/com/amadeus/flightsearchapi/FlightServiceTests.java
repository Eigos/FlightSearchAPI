package com.amadeus.flightsearchapi;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amadeus.flightsearchapi.dto.AirportInformationResponse;
import com.amadeus.flightsearchapi.dto.FlightInformation;
import com.amadeus.flightsearchapi.models.Airport;
import com.amadeus.flightsearchapi.models.Flight;
import com.amadeus.flightsearchapi.repositories.IAirportRepository;
import com.amadeus.flightsearchapi.repositories.IFlightRepository;
import com.amadeus.flightsearchapi.services.FlightService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FlightServiceTests {


    /*
    Tests are depentendt on real db
    could have been mocked
    */

    @Autowired
    private IFlightRepository flightRepository;

    @Autowired
    private IAirportRepository airportRepository;

    @Autowired
    private FlightService flightService;

    List<Airport> airports;
    List<Flight> flights;

    @BeforeAll
    public void prepareTestData() {

        airports = new ArrayList<>();
        flights = new ArrayList<>();

        airports.add(Airport.builder().ID(UUID.randomUUID()).city("CITY_A").build());
        airports.add(Airport.builder().ID(UUID.randomUUID()).city("CITY_B").build());

        flights.add(Flight.builder()
                .ID(UUID.randomUUID())
                .departureAirport(airports.get(0))
                .arrivalAirport(airports.get(1))
                .departureDate(DateTimeUtils.asDate(LocalDate.of(2024, 1, 1)))
                .arrivalDate(DateTimeUtils.asDate(LocalDate.of(2024, 1, 3)))
                .price(1000)
                .build());

        flights.add(Flight.builder()
                .ID(UUID.randomUUID())
                .departureAirport(airports.get(1))
                .arrivalAirport(airports.get(0))
                .departureDate(DateTimeUtils.asDate(LocalDate.of(2024, 1, 3)))
                .arrivalDate(DateTimeUtils.asDate(LocalDate.of(2024, 1, 4)))
                .price(500)
                .build());

        airportRepository.saveAll(airports);
        flightRepository.saveAll(flights);
    }

    @AfterAll
    public void cleanup(){
        flightRepository.deleteAll(flights);
        airportRepository.deleteAll(airports);
    }

    @Test
    public void Should_FindFlights_When_FlightsAreValid() {

        List<Flight> flightResults = flightService.findFlights(FlightInformation.builder()
                .departureAirport(AirportInformationResponse.builder().city(airports.get(0).getCity()).build())
                .arrivalAirport(AirportInformationResponse.builder().city(airports.get(1).getCity()).build())
                .departureDate(DateTimeUtils.asDate(LocalDate.of(2024, 1, 1)))
                .build());

        assertTrue(flightResults.size() == 1);

    }

    @Test
    public void Should_CouldNotFindFlights_When_FlightsAreInvalid() {

        List<Flight> flightResults = flightService.findFlights(FlightInformation.builder()
                .departureAirport(AirportInformationResponse.builder().city(airports.get(0).getCity()).build())
                .arrivalAirport(AirportInformationResponse.builder().city(airports.get(1).getCity()).build())
                .departureDate(DateTimeUtils.asDate(LocalDate.of(2024, 1, 2)))
                .build());

        assertTrue(flightResults.size() == 0);

    }

}
