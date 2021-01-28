package com.alicetin.PPMTool.web;

import com.alicetin.PPMTool.domain.Project;
import com.alicetin.PPMTool.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alicetin.PPMTool.services.ProjectService;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/project")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
   
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    
    
    @PostMapping("/create")
    public ResponseEntity<?> createNewProject (@Valid @RequestBody Project project, BindingResult result){
        
            ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
            
            if(errorMap != null)
                return errorMap;
            
            Project newProject = projectService.saveOrUpdateProject(project);
            return new ResponseEntity<Project>(newProject,HttpStatus.CREATED);
    }
           
    @GetMapping("/get/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){
        
        Project project = projectService.findProjectByIdentifier(projectId);
        
        return new ResponseEntity<Project>(project , HttpStatus.OK);
    } 
    
    @GetMapping("/get/all")
    public Iterable<Project> getAllProjects(){
        
        return projectService.findAllProjects();        
    }  
   
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        
        projectService.deleteProjectByIdentifier(projectId);
        
        return new ResponseEntity<String>("Project with ID: '"+projectId+"' was deleted!"
                ,HttpStatus.OK);
    }  
    
}

