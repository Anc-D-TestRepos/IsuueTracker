package org.training.issueTracker.web.controllers.statusControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;


@Controller
public class PrepareDataForEditStatusesController {

	private final String NAME  = "setName";
	private final String ID  = "setId";
	private final String OLD_STATUS  = "oldStatus";
	private final String SATUS_EDIT_PAGE = "statusEditingPage";
  
	@Autowired
	DAOInterface implDAO;
 
  
	@Autowired
 	Status status;
  
   
	public PrepareDataForEditStatusesController() {
      super();
     
	}
  
	@RequestMapping(value = "/PrepareDataForEditStatusesController")
	public String editType(@RequestParam (NAME) String oldName,@RequestParam (ID) int oldId, ModelMap model) {
	
	
	
		status.setId(oldId);
		status.setName(oldName);
		
		model.addAttribute(OLD_STATUS, status);
		
		return SATUS_EDIT_PAGE;
			
  }
	
	
}
