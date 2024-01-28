package com.amadeus.flightsearchapi.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class SigninRequest {
    
    private String username;
    private String password;
}
