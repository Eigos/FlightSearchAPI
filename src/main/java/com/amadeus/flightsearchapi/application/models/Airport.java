package com.amadeus.flightsearchapi.application.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    
    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID ID;
    
    private String city;

}
