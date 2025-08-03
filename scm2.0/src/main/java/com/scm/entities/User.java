package com.scm.entities;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

public class User {

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
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    //sign up info : SELF, GOOGLE, GITHUB
    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String provider_user_Id;

    //add more feilds if needed
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
    //matlab ek user ke pass multiple contacts ho sakte hain

    



}
