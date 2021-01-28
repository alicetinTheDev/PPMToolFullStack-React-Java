package com.alicetin.PPMTool.services;

import com.alicetin.PPMTool.domain.Project;
import com.alicetin.PPMTool.exceptions.ProjectIdException;
import com.alicetin.PPMTool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public Project saveOrUpdateProject(Project project){
        
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project Id: '" + project.getProjectIdentifier().toUpperCase() + "' is already exist..");
        }
    }
    
    public Project findProjectByIdentifier(String projectId){
        
        if(projectId.length() < 4 || projectId.length() > 5)
            throw new ProjectIdException("Project ID '" +projectId.toUpperCase()+ "' is not appropriate Project ID.. It should be 4 or 5 character..");
            
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        
        if (project == null){
            throw new ProjectIdException("Project ID '" +projectId.toUpperCase()+ "' does not exist..");
        }
        
        return project;
    }
    
    public Iterable<Project> findAllProjects(){
                
        Iterable<Project> projects = projectRepository.findAll();
        
        if (projects == null)
            return null;
        
        return projects;
    }
    
    public void deleteProjectByIdentifier(String projectId){
        
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        
        if (project == null)
            throw new ProjectIdException("Project ID: '"+projectId.toUpperCase()+"' does not exist!");
        else{
            projectRepository.delete(project);
        }
    } 
    
}
