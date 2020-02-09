package com.seniorproj.WebDaw.controller;

import com.seniorproj.WebDaw.exception.ResourceNotFoundException;
import com.seniorproj.WebDaw.model.User;
import com.seniorproj.WebDaw.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Register for an account
    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user)
    {
        user.setPass(bCryptPasswordEncoder.encode(user.getPass()));
        userRepository.save(user);
    }

    // CREATE (POST)

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User add(@RequestBody User user) {
        return userRepository.save(user);
    }

    // READ (GET)

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getOne(@PathVariable String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    // UPDATE

    @PutMapping(value = "/{id}")
    public User update(@PathVariable String id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        user.setName(updatedUser.getName());
        user.setPass(updatedUser.getPass());
        return userRepository.save(user);
    }

    // DELETE

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        userRepository.delete(user);
    }

}
