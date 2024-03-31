package com.cloud.assignment1_server.services;

import com.cloud.assignment1_server.dto.UserDto;
import com.cloud.assignment1_server.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserServiceInterface {

    public User create(
            UserDto userDto
    );

    public UserDetails loadUserByEmail(String email);

}
