package com.seniorproj.WebDaw.repo;

import com.seniorproj.WebDaw.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project, String> {
    @Override
    void delete(Project deleted);
    Optional<Project> findById(String id);
}
