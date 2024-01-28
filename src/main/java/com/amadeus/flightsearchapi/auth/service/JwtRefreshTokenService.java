package com.amadeus.flightsearchapi.auth.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amadeus.flightsearchapi.auth.models.RefreshToken;
import com.amadeus.flightsearchapi.auth.repositories.IRefreshTokenRepository;
import com.amadeus.flightsearchapi.auth.security.jwt.JwtAccessTokenUtils;

@Service
public class JwtRefreshTokenService {

    private final IRefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    JwtRefreshTokenService(IRefreshTokenRepository refreshTokenRepository,
            UserService userService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
    }

    public RefreshToken generateRefreshToken(UUID userId) {

        RefreshToken refreshToken = refreshTokenRepository.findByUser_ID(userId)
                .orElseGet(RefreshToken::new);

        refreshToken.setUser(userService.getByID(userId)
                .orElseThrow(() -> new RuntimeException("Unable to find user")));
        refreshToken.setExpireDate(Instant.now().plusMillis(JwtAccessTokenUtils.REFRESH_TOKEN_EXPIRATION_MS));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public Optional<RefreshToken> findByUserId(UUID userId) {
        return refreshTokenRepository.findByUser_ID(userId);
    }

    public boolean isExpired(RefreshToken token) {
        return token.getExpireDate().compareTo(Instant.now()) < 0;
    }

}
