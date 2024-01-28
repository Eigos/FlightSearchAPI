package com.amadeus.flightsearchapi.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenExpiredException extends RuntimeException {

    public RefreshTokenExpiredException(String token) {
        super(token + " Refresh token was expired. Please make a new signin request");
    }

}
