package com.amadeus.flightsearchapi.exceptions;

public class FlightNotFoundException extends RuntimeException{
 
    public FlightNotFoundException(){
        super("Flight not found");
    }

}
