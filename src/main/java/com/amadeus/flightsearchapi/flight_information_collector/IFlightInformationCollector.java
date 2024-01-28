package com.amadeus.flightsearchapi.flight_information_collector;

import java.util.List;

import com.amadeus.flightsearchapi.models.Flight;

public interface IFlightInformationCollector {
    
    List<Flight> requestFlights();

}
