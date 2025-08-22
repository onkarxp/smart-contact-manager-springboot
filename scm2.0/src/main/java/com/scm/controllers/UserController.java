package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


        //user dashboard endpoint
        @RequestMapping("/dashboard")
        public String userDashboard() {
            System.out.println("User dashboard accessed");
            return "user/dashboard";
        }

        //user profile endpoint
        @RequestMapping("/profile")
        public String userProfile() {
            System.out.println("User profile accessed");
            return "user/profile";
        }
}
