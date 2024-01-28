package com.amadeus.flightsearchapi.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenNotFoundException extends RuntimeException {

    public RefreshTokenNotFoundException(String token) {
        super(token + " Unable to find refresh token!");
    }

}
