package com.seniorproj.WebDaw.repo;

import com.seniorproj.WebDaw.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}