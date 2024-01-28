package com.amadeus.flightsearchapi.auth.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.amadeus.flightsearchapi.auth.models.RefreshToken;

public interface IRefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUser_ID(UUID userId);

}
