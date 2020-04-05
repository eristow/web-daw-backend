package com.seniorproj.WebDaw.controller;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seniorproj.WebDaw.configs.JwtTokenProvider;
import com.seniorproj.WebDaw.model.ApplicationUser;
import com.seniorproj.WebDaw.repo.UserRepository;
import com.seniorproj.WebDaw.services.CustomUserDetailsService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://web-daw-frontend.herokuapp.com"})
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthBody data) {
        try {
            String email = data.getEmail();
            String hash = this.userRepository.findByEmail(email).getPass();
            // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, data.getPass()));
            String token = jwtTokenProvider.createToken(email, this.userRepository.findByEmail(email).getRoles());
            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("token", token);
            model.put("hash", hash);
            return ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email/password supplied");
        }
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ApplicationUser user) {
        ApplicationUser userExists = userDetailsService.findUserByUsername(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("User with email: " + user.getEmail() + " already exists");
        }
        userDetailsService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);
    }
}
