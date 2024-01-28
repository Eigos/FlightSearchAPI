package com.amadeus.flightsearchapi.application.models;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID ID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departureAirport_id", referencedColumnName = "id")
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrivalAirport_id", referencedColumnName = "id")
    private Airport arrivalAirport;

    private Date departureDate;

    private Date arrivalDate;

    private long price;
}
