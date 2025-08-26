package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundException;
import com.scm.entities.User;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

@Service


public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        //we have to genrate userID dynamically also

        //so we will use UUID class to generate userID

        //password encoding
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set the user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        
        
        
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
    User user2 = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found with id"));
        
    //update karenge user2 ke fields ko from user
        user2.setName(user.getName());  
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setAbout(user.getAbout());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        //save the user in database
        User save = userRepo.save(user2);

        return Optional.ofNullable(save);
        //if save is null, it will return Optional.empty()
        //if save is not null, it will return Optional.of(save)
}

    @Override
    public void deleteUser(String id) {
         User user2 = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id"));
         userRepo.delete(user2);
         //this will delete the user from the database
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;  
        //if user2 is not null, it means user exists, so return true
        //if user2 is null, it means user does not exist, so return false}
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        //here an issue arises, because we dont have a method findByEmail in UserRepo
        //so we need to create a custom query method in UserRepo.
        User user2 = userRepo.findByEmail(email).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }
    
}
