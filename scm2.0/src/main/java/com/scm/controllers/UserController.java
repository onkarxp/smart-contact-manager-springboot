package com.scm.controllers;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

        @Autowired
        private UserService userService;

       



        Logger logger = Logger.getLogger(UserController.class.getName());

        //user dashboard endpoint
        @RequestMapping("/dashboard")
        public String userDashboard() {
            System.out.println("User dashboard accessed");
            return "user/dashboard";
        }

        //user profile endpoint
        @RequestMapping("/profile")
        public String userProfile(Model model,Authentication authentication) { //Model is used to send data from controller to view
           
            
            //this will be used in the profile.html page to show user details

            


            return "user/profile";
        }
}
