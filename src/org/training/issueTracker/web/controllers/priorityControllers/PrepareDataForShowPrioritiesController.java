package org.training.issueTracker.web.controllers.priorityControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller

public class PrepareDataForShowPrioritiesController {
 
	private final String CAUSE = "cause";
  	private final String DAO_ERROR_PAGE = "DAOErrPage";    
	private final String PRIORITY_PAGE = "priorityPage";
	private final String PRIORITY_LIST  = "priorityList";
    
	@Autowired
	DAOInterface implDAO;
 
  
	@Autowired
	Priority priority;
  
 
	public PrepareDataForShowPrioritiesController() {
      super();
     
	}
  
	
	@RequestMapping(value = "/PrepareDataForShowPrioritiesController")
    public String prepareType( ModelMap model) {
		
		try {
			List<Priority> priorityList = implDAO.getAllPriorities();
			
			model.addAttribute(PRIORITY_LIST, priorityList);
				
		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
		return	PRIORITY_PAGE ;
	
	}
}
