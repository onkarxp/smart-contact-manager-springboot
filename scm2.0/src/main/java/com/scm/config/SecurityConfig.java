package com.scm.config;



import java.security.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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

    @Autowired
    private OAuthSuccessHandler handler;

    

    @Bean
        public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                      PasswordEncoder passwordEncoder) {
                 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
                 daoAuthenticationProvider.setUserDetailsService(userDetailsServices);  // deprecated warning is fine for now
                 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);        // deprecated warning is fine for now
                 return daoAuthenticationProvider;
}


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        

        //login
        httpSecurity.authorizeHttpRequests(authorize -> {
            // authorize.requestMatchers("/home", "/register", "/services").permitAll(); //making /home a public 
            authorize.requestMatchers("/user/**").authenticated(); //everything under /user/ requires authentication
            authorize.anyRequest().permitAll();
            });

            //customizinng the default login form provided by spring security
            httpSecurity.formLogin(formLogin -> {

                formLogin.loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .successForwardUrl("/user/dashboard") //redirecting to user dashboard after successful login
                // .failureForwardUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password");
            });

            //logout

            //disabling CSRF for /logout 
            httpSecurity.csrf(AbstractHttpConfigurer::disable);
            httpSecurity.logout(logoutForm -> {
                logoutForm.logoutUrl("/do-logout");
                logoutForm.logoutSuccessUrl("/login?logout=true");
            });



            //oauth2 config
              httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(handler);
        });

        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });
       
        return httpSecurity.build();

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


