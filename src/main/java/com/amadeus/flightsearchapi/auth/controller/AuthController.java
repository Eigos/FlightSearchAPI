package com.amadeus.flightsearchapi.auth.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.flightsearchapi.auth.dto.LoginJwtResponse;
import com.amadeus.flightsearchapi.auth.dto.RefreshTokenRequest;
import com.amadeus.flightsearchapi.auth.dto.RefreshTokenResponse;
import com.amadeus.flightsearchapi.auth.dto.SigninRequest;
import com.amadeus.flightsearchapi.auth.dto.SignupRequest;
import com.amadeus.flightsearchapi.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private ModelMapper modelMapper;

    AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginJwtResponse> authenticateUser(@Valid @RequestBody SigninRequest signinRequest) {
    var loginResult = authService.authenticateUser(
            signinRequest.getUsername(),  
            signinRequest.getPassword()
        );

        var loginResponse = modelMapper.map(loginResult, LoginJwtResponse.class);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        authService.registerUser(signUpRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authService.generateTokensByRefreshToken(refreshTokenRequest.getRefreshToken()));
    }

}
