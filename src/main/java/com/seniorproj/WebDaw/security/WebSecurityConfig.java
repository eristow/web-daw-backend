package com.seniorproj.WebDaw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Defined 3 users for testing. Will need to be changed to pull from Mongodb
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
            .and()
            .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
            .and()
            .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/", "/home", "/login*").permitAll() // This line specifies which URL paths should not require authentication
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage.html", true)
                .failureHandler(authenticationFailureHandler())
                .and()
            .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JESESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
            User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
