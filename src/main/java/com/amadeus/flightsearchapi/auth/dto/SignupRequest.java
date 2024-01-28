package com.amadeus.flightsearchapi.auth.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
    
    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*$")
    private String username;

    @Size(min = 2, max = 20)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*$")
    private String password;
}
