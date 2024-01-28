package com.amadeus.flightsearchapi.repositories;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amadeus.flightsearchapi.models.User;

import java.util.Optional;
import java.util.UUID;

@Repository
@EnableJpaRepositories
public interface IUserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}