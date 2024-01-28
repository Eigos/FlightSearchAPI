package com.amadeus.flightsearchapi.util;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.amadeus.flightsearchapi.application.models.Airport;
import com.amadeus.flightsearchapi.application.repositories.IAirportRepository;

@Component
public class AirpotDefinitions
        implements ApplicationListener<ApplicationReadyEvent> {

    private final IAirportRepository airportRepository;

    private final String[] airportNameList = {
            "Cabinda",
            "Cabo",
            "Cagliari",
            "Cairns",
            "Cairo",
            "Calama",
            "Calcutta",
            "Calgary",
            "Cali",
            "Calicut",
            "Calvi",
            "Cambridge",
            "Cambrigde",
            "Campbeltown",
            "Campo",
            "Canberra",
            "Cancun",
            "Canouan",
            "Cape",
            "Caracas",
            "Cardiff",
            "Carlsbad",
            "Carnarvon",
            "Carnot",
            "Carson",
            "Cartagena",
            "LaRomana",
            "Casablanca",
            "Casablanca",
            "Casablanca",
            "Casper",
            "Castries",
            "Catania",
            "Cayenne",
            "Cedar",
            "Ceduna",
            "Cessnock",
            "Chambery"
    };

    AirpotDefinitions(IAirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        Stream.of(airportNameList)
                .map(v -> Airport.builder()
                        .city(v)
                        .build())
                .collect(Collectors.toList())
                .stream()
                .forEach(v -> {
                    if(!airportRepository.existsByCity(v.getCity()))
                        airportRepository.save(v);
                });

    }
}
