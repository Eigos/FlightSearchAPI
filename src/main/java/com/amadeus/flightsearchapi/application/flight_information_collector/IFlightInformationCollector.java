package com.amadeus.flightsearchapi.application.flight_information_collector;

import java.util.List;

import com.amadeus.flightsearchapi.application.models.Flight;

public interface IFlightInformationCollector {
    
    List<Flight> requestFlights();

}
