package com.scm.services;

import com.scm.entities.User;
import java.util.List;
import java.util.Optional;


public interface UserService {
    User saveUser(User user);

    Optional<User> getUserById(String id);
    //optional is used to handle the case where a user might not be found

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserExist(String userId);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    //add more methopds related to user logic as needed

}
