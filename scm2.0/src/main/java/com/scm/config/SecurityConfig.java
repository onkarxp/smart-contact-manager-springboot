package com.scm.config;


import java.security.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.scm.services.impl.SecurityCustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Everything written below is just for testing as it is in-memory authentication
    //but we need to implement a proper authentication with database
    
//     public UserDetailsService userDetailsService() {
//         UserDetails user1 = User
//             .withDefaultPasswordEncoder()
//             .username("admin123")
//             .password("admin123")
//             .roles("USER", "ADMIN")
//             .build();

//         UserDetails user2 = User
//             .withUsername("user123")
//             .password("password")
//             .roles("USER")
//             .build();

//         return new InMemoryUserDetailsManager(user1, user2);
//     }


    @Autowired
    private SecurityCustomUserDetailsService userDetailsServices;

    @Bean
        public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                      PasswordEncoder passwordEncoder) {
                 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
                 daoAuthenticationProvider.setUserDetailsService(userDetailsServices);  // deprecated warning is fine for now
                 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);        // deprecated warning is fine for now
                 return daoAuthenticationProvider;
}


    @Bean
    public PasswordEncoder passwordEncoders() {
        return new BCryptPasswordEncoder();
    }

    // Temporary in-memory user store for testing
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User
            .withUsername("admin123")
            .password(passwordEncoder.encode("admin123"))
            .roles("USER", "ADMIN")
            .build();

        UserDetails user2 = User
            .withUsername("user123")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}


