package com.scm.controllers;

import java.security.Principal;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.helper.Helper;

@Controller
@RequestMapping("/user")
public class UserController {


        Logger logger = Logger.getLogger(UserController.class.getName());

        //user dashboard endpoint
        @RequestMapping("/dashboard")
        public String userDashboard() {
            System.out.println("User dashboard accessed");
            return "user/dashboard";
        }

        //user profile endpoint
        @RequestMapping("/profile")
        public String userProfile(Authentication authentication) {
            
            String username = Helper.getEmailofLoggedInUser(authentication);
            logger.info("User logged in: " + username);


            //db se user ko detch kar sakte hai
            System.out.println("user profile accessed");
            return "user/profile";
        }
}
