package com.amadeus.flightsearchapi.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightInformation {
    
    private AirportInformationResponse departureAirport;
    private AirportInformationResponse arrivalAirport;
    private Date departureDate;
    private Date arrivalDate;

}
