package org.training.issueTracker.web.controllers.projectControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Build;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller
public class ProjectEditController {


	private final String CAUSE = "cause";
  	private final String DAO_ERROR_PAGE = "DAOErrPage";    
  	private final String SCSFL_PAGE = "scssfulAddingData";
	private final String  NAME = "name";
	private final String  DESCRIPTION = "description";
	private final String  BUILD = "build";
	private final String  NEW_BUILD = "newBuild";
	private final String  MANAGER = "manager";
	private final String  PROJECT_ID = "projId";
	
	@Autowired
	DAOInterface implDAO;
 
  
	@Autowired
	Project project;
	
	@Autowired
	Build build;
 
	public ProjectEditController() {
      super();
     
	}
  
	
	@RequestMapping(value = "/ProjectEditController")
    public String prepareType( @RequestParam (PROJECT_ID) int idProject, @RequestParam (NAME) String name,
    						   @RequestParam (DESCRIPTION) String description, @RequestParam (BUILD) String oldBuild,
    						   @RequestParam (value= NEW_BUILD, required=false) String newBuild, @RequestParam (MANAGER) String manager,
    						   ModelMap model) {
		

		if ((oldBuild==null)||(oldBuild.trim().isEmpty())) {
			oldBuild = newBuild;
		}

		try {
			
			 project.setId(idProject);
			 project.setName(name);
			 project.setDescription(description);
			 project.setManager(manager);
			  
			build.setName(oldBuild); 
			
			implDAO.editProject(project,build);
					
		
		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
		return SCSFL_PAGE;
	
	}
}



