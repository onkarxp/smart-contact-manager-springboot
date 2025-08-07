package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.forms.UserForm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


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
        
    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }
    
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/register")
    public String register(Model model)
     {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
        // this register is the name of the view (register.html)
        // The model attribute "userForm" will be used to bind the form data to the UserForm object in the view
        // This allows us to use the form in the register.html template  
        // The UserForm class is a simple POJO (Plain Old Java Object) that contains fields for user registration
        // such as name, email, password, about, and phoneNumber.
     }


    @RequestMapping(value="/do-register", method = RequestMethod.POST)
    //This method will handle the registration form submission
    public String processRegister(@ModelAttribute UserForm userForm){
            System.out.println("Processing registration form");

            //fetch form data
            //validate form data
            //save to database
            //message = "Registration successful"
            System.out.println(userForm);
        
            return "redirect:/register"; //Redirecting to login page after registration
    }
}
