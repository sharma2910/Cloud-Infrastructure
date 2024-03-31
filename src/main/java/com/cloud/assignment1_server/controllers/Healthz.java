package com.cloud.assignment1_server.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/healthz")
public class Healthz {
    DataSource dataSource;

    @Autowired
    public Healthz(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @GetMapping("")
    public ResponseEntity<String> getEndpoint(
            HttpServletResponse response,
            @RequestParam Map<String,String> allRequestParams,
            @RequestBody(required = false) Object requestBody
    ) throws SQLException {
        response.setHeader("cache-control", "no-cache");
         if(!allRequestParams.isEmpty() || requestBody != null){
            return new ResponseEntity<>("", HttpStatusCode.valueOf(400));
        }
        if(!dataSource.getConnection().isValid(0)){
            System.out.println("Datasource invalid");
        }
        return new ResponseEntity<>("", HttpStatusCode.valueOf(200));
    }
}
