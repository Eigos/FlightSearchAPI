package com.amadeus.flightsearchapi.services;

import org.springframework.stereotype.Service;

import com.amadeus.flightsearchapi.models.User;
import com.amadeus.flightsearchapi.repositories.IUserRepository;

import java.util.*;

@Service
public class UserService implements ICrudService<User> {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getByID(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(User entity) {
        userRepository.save(entity);
        return entity;
    }

    @Override
    public User update(User entity) throws Exception {
        var user = userRepository.findById(entity.getID());
        if(user.isEmpty()){
            throw new Exception("User not found!");
        }

        userRepository.save(entity);
        return entity;
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
}
