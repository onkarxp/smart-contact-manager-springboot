package com.scm.controllers;


import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.AppConstants;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {


    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    private Logger logger = LoggerFactory.getLogger(ContactController.class);



    @RequestMapping("/add") 
    public String addContactView(Model model) {

        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
    
    
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //WE are using ModelAttribute to bind the form data to the ContactForm object
    //BindingResult is used to check if there are any validation errors
    //Authentication is used to get the logged in user details
    //HttpSessio is used here, to display the success message after adding the contact
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult result, Authentication authentication, HttpSession session) {

        System.out.println("This is form: "+contactForm);



        String userName = Helper.getEmailOfLoggedInUser(authentication);
        //process the form data

        User user = userService.getUserByEmail(userName);

        //adding validations
        if(result.hasErrors()){
            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }

        //image upload karne ka code
        String fileName = UUID.randomUUID().toString();

        String fileURL = imageService.uploadImage(contactForm.getContactImage(), fileName);

        logger.info("file information :{}",contactForm.getContactImage().getOriginalFilename());

        //converting form to contact entity
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavourite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(fileName);
        contactService.save(contact);
        
        System.out.println(contactForm.getName());

        //success message
        session.setAttribute("message",
                Message.builder()
                        .content("You have successfully added a new contact")
                        .type(MessageType.green)
                        .build());


        return "redirect:/user/contacts/add";

    }

    @RequestMapping()
    public String viewcontacts(
        // All these additional parameters are for pagination and sorting
    @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,Authentication authentication, Model model) {
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user,page,size,sortBy,direction);

        model.addAttribute("pageContact", pageContact);


        return "user/contacts";

        }
}


