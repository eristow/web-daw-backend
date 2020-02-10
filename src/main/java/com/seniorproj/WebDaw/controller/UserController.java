package com.seniorproj.WebDaw.controller;

import com.seniorproj.WebDaw.exception.ResourceNotFoundException;

import com.seniorproj.WebDaw.model.ApplicationUser;
import com.seniorproj.WebDaw.repo.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Register for an account
    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser applicationUser)
    {
        applicationUser.setPass(bCryptPasswordEncoder.encode(applicationUser.getPass()));
        applicationUserRepository.save(applicationUser);
    }

    // CREATE (POST)

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ApplicationUser add(@RequestBody ApplicationUser applicationUser) {
        return applicationUserRepository.save(applicationUser);
    }

    // READ (GET)

    @GetMapping
    public List<ApplicationUser> getAll() {
        return applicationUserRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ApplicationUser getOne(@PathVariable String id) {
        return applicationUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    // UPDATE

    @PutMapping(value = "/{id}")
    public ApplicationUser update(@PathVariable String id, @RequestBody ApplicationUser updatedApplicationUser) {
        ApplicationUser applicationUser = applicationUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        applicationUser.setName(updatedApplicationUser.getName());
        applicationUser.setPass(updatedApplicationUser.getPass());
        return applicationUserRepository.save(applicationUser);
    }

    // DELETE

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void delete(@PathVariable String id) {
        ApplicationUser applicationUser = applicationUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        applicationUserRepository.delete(applicationUser);
    }
}
