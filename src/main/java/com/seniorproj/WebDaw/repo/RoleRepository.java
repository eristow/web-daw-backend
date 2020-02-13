package com.seniorproj.WebDaw.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.seniorproj.WebDaw.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByRole(String role);
}
