package com.amadeus.flightsearchapi.application.exceptions;

public class FlightNotFoundException extends RuntimeException{
 
    public FlightNotFoundException(){
        super("Flight not found");
    }

}
