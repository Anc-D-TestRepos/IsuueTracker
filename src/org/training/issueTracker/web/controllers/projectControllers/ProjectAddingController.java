package org.training.issueTracker.web.controllers.projectControllers;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Build;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller
public class ProjectAddingController {

    private final String CAUSE = "cause";
    private final String DAO_ERROR_PAGE = "DAOErrPage";    
	private final String SCSFL_PAGE = "scssfulAddingData";
	private final String  NAME = "name";
	private final String  DESCRIPTION = "description";
	private final String  BUILD = "build";
	private final String  MANAGER = "manager";
    
    
    @Autowired
    DAOInterface implDAO;
         
    @Autowired
    Project project;
    
    @Autowired
    Build build;
    

    public ProjectAddingController() {
        super();
       
    }
    
    @RequestMapping(value = "/ProjectAddingController",  method = RequestMethod.POST)
    
   
    public String addType(@RequestParam (NAME) String name,@RequestParam (DESCRIPTION) String description,
    					  @RequestParam (BUILD) String newBuild,@RequestParam (MANAGER) String manager,
    					  ModelMap model,HttpSession httpSession) {

    	    	

		if (description.length()>250){
			description = description.substring(0, 245)+"...";
			
		}
		
		
		project.setName(name);
		project.setDescription(description);
		project.setManager(manager);
		
		build.setName(newBuild);


		try {
			
			
			implDAO.addProject(project, build);
			} catch (ClassNotFoundException | DAOException e) {
		
				httpSession.setAttribute(CAUSE, e.getMessage());
				return DAO_ERROR_PAGE;
				
			}
			
		return SCSFL_PAGE;
	
	}
             
}
