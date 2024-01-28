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
public class FlightInformationResponse {

    FlightInformation flightInformation;

    private long price;

}
