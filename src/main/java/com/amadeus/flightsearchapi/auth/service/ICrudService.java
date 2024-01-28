package com.amadeus.flightsearchapi.auth.service;

import java.util.Optional;
import java.util.UUID;

public interface ICrudService<T> {
    Iterable<T> getAll();
    Optional<T> getByID(UUID id);
    T create(T entity);
    T update(T entity) throws Exception;
    void delete(UUID id);
}
