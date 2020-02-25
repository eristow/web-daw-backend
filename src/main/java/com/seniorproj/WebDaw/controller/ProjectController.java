package com.seniorproj.WebDaw.controller;

import com.seniorproj.WebDaw.model.Project;
import com.seniorproj.WebDaw.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(method=RequestMethod.GET, value="/api/projects")
    public List<Project> project() {
        return projectRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/api/projects")
    public String save(@RequestBody Project project) {
        projectRepository.save(project);

        return project.getId();
    }

    @RequestMapping(method=RequestMethod.GET, value="/api/projects/{id}")
    public Optional<Project> show(@PathVariable String id) {
        return projectRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/api/projects/{id}")
    public Project update(@PathVariable String id, @RequestBody Project project) {
        Optional<Project> proj = projectRepository.findById(id);
        if(project.getProjName() != null)
            proj.get().setProjName(project.getProjName());
        if(project.getProjDuration() != null)
            proj.get().setProjDuration(project.getProjDuration());
        if(project.getProjDateCreated() != null)
            proj.get().setProjDateCreated(project.getProjDateCreated());
        projectRepository.save(proj.get());
        return proj.get();
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/api/projects/{id}")
    public String delete(@PathVariable String id) {
        Optional<Project> project = projectRepository.findById(id);
        projectRepository.delete(project.get());

        return "project deleted";
    }
}
