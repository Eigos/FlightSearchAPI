package com.amadeus.flightsearchapi.exceptions;

public class AirportNotFoundException extends RuntimeException{
 
    public AirportNotFoundException(){
        super("Airport not found");
    }

}
