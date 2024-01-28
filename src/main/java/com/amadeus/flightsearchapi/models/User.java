package com.amadeus.flightsearchapi.models;

import java.util.Set;
import java.util.UUID;

import com.amadeus.flightsearchapi.auth.models.AccountStatus;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;
    
    private String username;
    private String password;

    @ElementCollection(targetClass = AccountStatus.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "account_roles")
    @Column(name = "roles")
    @EqualsAndHashCode.Exclude
    private Set<AccountStatus> accountStatus;

}
