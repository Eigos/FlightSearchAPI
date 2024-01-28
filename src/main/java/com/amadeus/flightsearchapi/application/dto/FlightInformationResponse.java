package com.amadeus.flightsearchapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightInformationResponse {

    FlightInformation flightInformation;

    private long price;

}
