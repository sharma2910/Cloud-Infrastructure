package com.cloud.assignment1_server.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    @NotNull()
    private String firstName;
    @NotNull()
    private String lastName;
    @NotNull()
    private String email;
    @NotNull()
    private String password;

}
