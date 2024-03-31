package com.cloud.assignment1_server.repositories;

import com.cloud.assignment1_server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User getUserByEmail(String email);

}