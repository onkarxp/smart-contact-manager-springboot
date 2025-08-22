package com.scm.entities;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User implements UserDetails {

    @Id
    private String userId;
    @Column(name = "user_name",nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String password;
    @Lob
    @Column(length = 1000)
    private String about;
    @Lob
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;
    //information
    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    //sign up info : SELF, GOOGLE, GITHUB
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    //add more feilds if needed
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
    //matlab ek user ke pass multiple contacts ho sakte hain


    @ElementCollection(fetch = FetchType.EAGER)  //data base me alag table banega roles ke liye
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Yaha basically hum roles[USER,ADMIN] ko "Collection" SimpleGrantedAuthority[roles{USER,ADMIN}] me convert kar rahe hain
       Collection <SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
       //What is Stream? = A stream is a sequence of elements that can be processed in parallel or sequentially.
       //What is Collectors? = Collectors is a utility class that provides various methods to collect elements from a stream into a collection or other data structures.
       //what is SimpleGrantedAuthority? = SimpleGrantedAuthority is a class that implements the GrantedAuthority interface and represents a single authority granted to an authentication object.
       return roles;
    }


        //for this project our email id is username
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return this.enabled;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    



}
