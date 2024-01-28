package com.amadeus.flightsearchapi.auth.models;

import java.time.Instant;
import java.util.UUID;

import com.amadeus.flightsearchapi.models.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RefreshTokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Instant expireDate;

}
