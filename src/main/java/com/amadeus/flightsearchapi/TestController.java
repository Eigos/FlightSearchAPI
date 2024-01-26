package com.amadeus.flightsearchapi;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1/")
public class TestController {
    
    @GetMapping
    public ResponseEntity<String> greetEndpoint(){
        return ResponseEntity.ok("Hello there!");
    }

}
