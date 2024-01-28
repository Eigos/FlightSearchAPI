package com.amadeus.flightsearchapi.auth.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtLoginResult {
    private String accessToken;
    private String refreshToken;
    private String username;
    private List<AccountStatus> accountStatus;
}
