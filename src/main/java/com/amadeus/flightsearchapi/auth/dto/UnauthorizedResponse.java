package com.amadeus.flightsearchapi.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnauthorizedResponse {

    private int status;
    private String error;
    private String message;
    private String path;
}
