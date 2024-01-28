package com.amadeus.flightsearchapi.auth.dto;

import java.util.List;

import com.amadeus.flightsearchapi.auth.models.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginJwtResponse {

    private String accessToken;
    private String refreshToken;
    private String username;
    private List<AccountStatus> accountStatus;
}
