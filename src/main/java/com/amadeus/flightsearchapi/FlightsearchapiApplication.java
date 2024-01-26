package com.amadeus.flightsearchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flight Search API"))
public class FlightsearchapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightsearchapiApplication.class, args);
	}

}
