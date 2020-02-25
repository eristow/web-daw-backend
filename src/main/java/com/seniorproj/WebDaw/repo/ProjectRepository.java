package com.seniorproj.WebDaw.repo;

import com.seniorproj.WebDaw.model.Product;
import com.seniorproj.WebDaw.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
    @Override
    void delete(Project deleted);
}
