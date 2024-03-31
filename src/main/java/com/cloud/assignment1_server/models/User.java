package com.cloud.assignment1_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull(message = "firstName cannot be empty")
    private String firstName;

    @NotNull(message = "lastName cannot be empty")
    private String lastName;

    @NotNull(message = "email cannot be empty")
    private String email;

    @NotNull(message = "password cannot be empty")
    private String password;

}
