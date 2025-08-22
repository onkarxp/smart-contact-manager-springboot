package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

 // This class represents the user registration form
// It can be used to capture user details during registration   
public class UserForm {


    //we have to add validation annotations here 
    //now, these validations will be used to validate the user input
    // when the user submits the registration form
    // if the input is invalid, an error message will be returned to the user

    //These now will also be used in the controller to validate the user input
    // and return appropriate error messages

    @NotBlank(message = "Name is required")
    @Size(min=3,message = "Name must be at least 3 characters long")
    private String name;


    @NotBlank(message = "Email is required")
    @Email(message = " Invalid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min=6, message = "Password must be at least 6 characters long")
    private String password;

    @NotBlank(message = "About is required")
    private String about;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{8,12}", message = "Phone number must be between 8 and 12 digits")
    private String phoneNumber;
    
}
