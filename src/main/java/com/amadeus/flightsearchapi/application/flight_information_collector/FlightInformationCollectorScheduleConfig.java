package com.amadeus.flightsearchapi.application.flight_information_collector;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.amadeus.flightsearchapi.application.services.FlightService;

@Configuration
@EnableScheduling
public class FlightInformationCollectorScheduleConfig {

    private final IFlightInformationCollector flightInformationCollector;
    private final FlightService flightService;

    private static final long TASK_PERFORM_DELAY_MS = 24 * 60 * 60 * 1000;

    public FlightInformationCollectorScheduleConfig(
            @Qualifier("offlineCollector") IFlightInformationCollector flightInformationCollector,
            FlightService flightService) {
        this.flightInformationCollector = flightInformationCollector;
        this.flightService = flightService;
    }

    @Scheduled(fixedRate = TASK_PERFORM_DELAY_MS)
    public void fetchData() {
        flightService.saveAll(flightInformationCollector.requestFlights());

    }

}
