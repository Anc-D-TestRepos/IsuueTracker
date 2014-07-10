package org.training.issueTracker.web.controllers.priorityControllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller
public class PriorityEditingController {

	private final String CAUSE = "cause";
	private final String RETURN_PAGE = "page";
	  private final String COLOR = "black";
	private final String PAGE = "/priorityEditingPage.jsp"; 
	private final String NEW_PRIORITY = "newPriority";
	private final String PRIORITY = "Priority";
	private final String OLD_ID = "oldId";
	private final String BAD_FIELD = "badField";
	private final String ADD_ERROR_PAGE = "errEditingData";
	private final String EMPTY_FIELDS = "emptyField";
	private final String SCSFL_PAGE = "scssfulAddingData";
	private final String DAO_ERROR_PAGE = "DAOErrPage";	

    
	@Autowired
	DAOInterface implDAO;
 

	@Autowired
	Priority priority;
  
 
	public PriorityEditingController() {
      super();
     
	}
  
	@RequestMapping(value = "/PriorityEditingController",  method = RequestMethod.POST)
  
 
 
  
	public String editType(@RequestParam (NEW_PRIORITY) String newPriority,@RequestParam (OLD_ID) int oldId, ModelMap model) {
		
	
		if ((newPriority==null)||(newPriority.trim().isEmpty())){
			
			
			List <String> badFields = new ArrayList<>();
			badFields.add(PRIORITY);
			model.addAttribute(BAD_FIELD, badFields);
			model.addAttribute(CAUSE,EMPTY_FIELDS );
			model.addAttribute(RETURN_PAGE,PAGE );
			
			return	ADD_ERROR_PAGE ;
			
			
		}else {
			try {
				
			
				priority.setId(oldId);  
				priority.setName(newPriority);
				priority.setColor(COLOR);
				implDAO.updatePriority(priority);
				
				
			} catch (ClassNotFoundException | DAOException e) {
			
				model.addAttribute(CAUSE, e.getMessage());
				return DAO_ERROR_PAGE;
				
			}
		}
	
		return SCSFL_PAGE;
		
	}
}
