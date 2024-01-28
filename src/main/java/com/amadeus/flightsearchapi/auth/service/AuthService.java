package com.amadeus.flightsearchapi.auth.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amadeus.flightsearchapi.auth.dto.RefreshTokenResponse;
import com.amadeus.flightsearchapi.auth.dto.SignupRequest;
import com.amadeus.flightsearchapi.auth.exceptions.RefreshTokenNotFoundException;
import com.amadeus.flightsearchapi.auth.models.AccountStatus;
import com.amadeus.flightsearchapi.auth.models.JwtLoginResult;
import com.amadeus.flightsearchapi.auth.models.RefreshToken;
import com.amadeus.flightsearchapi.auth.models.User;
import com.amadeus.flightsearchapi.auth.repositories.IUserRepository;
import com.amadeus.flightsearchapi.auth.security.jwt.JwtAccessTokenUtils;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtAccessTokenUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtRefreshTokenService refreshTokenService;

    AuthService(
            AuthenticationManager authenticationManager,
            JwtAccessTokenUtils jwtUtils,
            IUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserService userService,
            JwtRefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
    }

    public JwtLoginResult authenticateUser(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<AccountStatus> accountStatus = userDetails.getAuthorities().stream()
                .map(item -> AccountStatus.valueOf(item.getAuthority()))
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(userDetails.getId());

        return JwtLoginResult.builder()
                .accessToken(jwt)
                .refreshToken(refreshToken.getToken())
                .username(userDetails.getUsername())
                .accountStatus(accountStatus)
                .build();
    }

    public void registerUser(String username, String password) throws RuntimeException {

        if (userService.existsByUsername(username)) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .accountStatus(Set.of(AccountStatus.User))
                .build();

        userService.create(user);
    }

    public void registerUser(SignupRequest signUpRequest) throws RuntimeException {

        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .accountStatus(Set.of(AccountStatus.User))
                .build();

        userService.create(user);
    }

    public RefreshTokenResponse generateTokensByRefreshToken(String refreshToken) {

        RefreshToken token = refreshTokenService.findByToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenNotFoundException(refreshToken));

        return RefreshTokenResponse.builder()
                .refreshToken(refreshTokenService.generateRefreshToken(token.getUser().getID()).getToken())
                .accessToken(jwtUtils.generateTokenFromUsername(token.getUser().getUsername()))
                .build();
    }

}
