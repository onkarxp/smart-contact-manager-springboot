package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    //THis will contain all the methods to interact with the database
    //custom query methods

    //custom finder methods
          //1. Here i am writing a custom query method to find user by email

          Optional <User> findByEmail(String email);
    
}
