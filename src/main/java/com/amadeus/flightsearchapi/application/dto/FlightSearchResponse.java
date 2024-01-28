package com.amadeus.flightsearchapi.application.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchResponse {
    
    List<FlightInformationResponse> flightDeparture;

    @JsonInclude(value = Include.NON_NULL)
    List<FlightInformationResponse> flightReturn;

}
