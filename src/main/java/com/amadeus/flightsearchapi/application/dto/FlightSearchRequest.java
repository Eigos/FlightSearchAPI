package com.amadeus.flightsearchapi.application.dto;

import java.util.Date;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSearchRequest {
    
    @NotNull
    private AirportInformationResponse departureAirportCity;

    @NotNull
    private AirportInformationResponse arrivalAirportCity;
    
    @FutureOrPresent
    private Date departureDate;

    @Null
    private Date returnDate;

}
