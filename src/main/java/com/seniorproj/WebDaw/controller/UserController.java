package com.seniorproj.WebDaw.controller;

import com.seniorproj.WebDaw.exception.ResourceNotFoundException;
import com.seniorproj.WebDaw.model.ApplicationUser;
import com.seniorproj.WebDaw.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // CREATE (POST)

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ApplicationUser add(@RequestBody ApplicationUser user) {
        return userRepository.save(user);
    }

    // READ (GET)

    @GetMapping
    public List<ApplicationUser> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ApplicationUser getOne(@PathVariable String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    // UPDATE

    @PutMapping(value = "/{id}")
    public ApplicationUser update(@PathVariable String id, @RequestBody ApplicationUser updatedUser) {
        ApplicationUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        user.setUsername(updatedUser.getUsername());
        user.setPass(updatedUser.getPass());
        return userRepository.save(user);
    }

    // DELETE

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        ApplicationUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        userRepository.delete(user);
    }

}
