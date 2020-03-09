package com.seniorproj.WebDaw.repo;

import com.seniorproj.WebDaw.model.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<ApplicationUser, String> {
    ApplicationUser findByEmail(String email);
}