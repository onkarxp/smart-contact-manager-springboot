package com.scm.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {


    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    ;
    
        @ModelAttribute
        public void addLoggedInUserInformation(Model model, Authentication authentication){
            if(authentication == null) return;
            System.out.println("Adding logged in user info to model");

            String username = Helper.getEmailofLoggedInUser(authentication);
            logger.info("User logged in: " + username);
            //db se user ko detch kar sakte hai
            User user = userService.getUserByEmail(username); 
            System.out.println(user);
            //no need to add if else because handled in UserServiceImplementation class
           
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            model.addAttribute("loggedInUser", user);
            
            }
        }
            
        
    

