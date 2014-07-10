package org.training.issueTracker.web.controllers.priorityControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;


@Controller
public class PrepareDataForEditPriorityController {

	private final String OLD_PRIORITY  = "oldPriority";
	private final String PRIORITY_EDIT_PAGE = "priorityEditingPage";
	private final String NAME  = "setName";
	private final String ID = "setId";
	
	
	@Autowired
	DAOInterface implDAO;

  
	@Autowired
	Priority priority;
  
 
	public PrepareDataForEditPriorityController() {
      super();
     
	}
  
  @RequestMapping(value = "/PrepareDataForEditPriorityController")
  
 public String editType(@RequestParam (NAME) String oldName,@RequestParam (ID) int oldId, ModelMap model) {
  	
  	priority.setId(oldId);
  	priority.setName(oldName);
	
	model.addAttribute(OLD_PRIORITY, priority);
		
	return PRIORITY_EDIT_PAGE;
		
	}
		
}