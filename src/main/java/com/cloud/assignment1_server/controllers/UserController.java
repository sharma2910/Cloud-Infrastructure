package com.cloud.assignment1_server.controllers;

import com.cloud.assignment1_server.dto.UserDto;
import com.cloud.assignment1_server.models.User;
import com.cloud.assignment1_server.services.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {



    UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createNewUser(
            @RequestParam Map<String,String> allRequestParams,
            @RequestBody(required = true) @Valid UserDto userDto
    ){
        if(!allRequestParams.isEmpty()) {
            return new ResponseEntity<>("", HttpStatusCode.valueOf(400));
        }
        User newUser = userService.create(userDto);
        return new ResponseEntity<>(newUser, HttpStatusCode.valueOf(201));
    }

}
