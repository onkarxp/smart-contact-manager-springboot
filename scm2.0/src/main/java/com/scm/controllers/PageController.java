package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");

        //Sending data to view on HTML Page

        //.addAttribute is used to send data to the view $ acts as a key-value pair
        model.addAttribute("name", "Substring technologies");
        model.addAttribute("youtubeChannel", "Ozone");
        model.addAttribute("description", "This is a SCM project for managing supply chain operations.");
        model.addAttribute("codolio", "https://codolio.com/profile/onkarxp");
        return "home"; //this home is the name of the view (home.html)
    }

    //about page handler
    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("isLogin", true);
        
        System.out.println("About page loading");
        return "about"; //this about is the name of the view (about.html)
    }

    //service page handler
    @RequestMapping("/services")
    public String services(){
        System.out.println("Services page loading");
        return "services"; //this services is the name of the view (services.html)
    }
}
