package org.training.issueTracker.web.controllers.projectControllers;


import java.util.Set;
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
public class PrepareDataForEditProjectController {


	private final String CAUSE = "cause";
  	private final String DAO_ERROR_PAGE = "DAOErrPage";    
  	private final String PROJECT_EDIT_PAGE = "projectEditPage";
	private final String PROJECT ="project";
	private final String PROJECT_ID ="projectId";
	private final String BUILD ="buildList";
  
  
	@Autowired
	DAOInterface implDAO;
 
	@Autowired
	Project project;
  
	@Autowired
	Build build;
 
	
	public PrepareDataForEditProjectController() {
      super();
     
	}
  
	
	@RequestMapping(value = "/PrepareDataForEditProjectController")
    public String prepareType(@RequestParam (PROJECT_ID) int projectId, ModelMap model) {
		
		try {
		
			Project  project = implDAO.getProjectById(projectId);
							
			Set<Build> buildList =  (Set<Build>) project.getBuilds();
		
			model.addAttribute(PROJECT, project);
			
			model.addAttribute(BUILD, buildList);
			
		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
		return	PROJECT_EDIT_PAGE ;
	
	}
}
