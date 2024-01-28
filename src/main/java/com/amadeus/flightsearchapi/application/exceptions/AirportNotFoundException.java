package com.amadeus.flightsearchapi.application.exceptions;

public class AirportNotFoundException extends RuntimeException{
 
    public AirportNotFoundException(){
        super("Airport not found");
    }

}
