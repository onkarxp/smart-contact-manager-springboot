package com.scm.forms;

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
    private String name;
    private String email;
    private String password;
    private String about;
    private String phoneNumber;
    
}
