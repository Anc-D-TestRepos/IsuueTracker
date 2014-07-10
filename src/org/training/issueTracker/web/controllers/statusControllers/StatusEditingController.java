package org.training.issueTracker.web.controllers.statusControllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller
public class StatusEditingController {

  private final String CAUSE = "cause";
  private final String NEW_STATUS = "newStatus";
  private final String STATUS = "Status";
  private final String ID= "oldId";
  private final String DAO_ERROR_PAGE = "DAOErrPage";    
  private final String ADD_ERROR_PAGE = "errEditingData";
  private final String RETURN_PAGE = "page";
  private final String PAGE = "/statusEditingPage.jsp";
  private final String EMPTY_FIELDS = "emptyField";
  private final String BAD_FIELD = "badField";
  private final String SCSFL_PAGE = "scssfulAddingData";
  
  
  @Autowired
  DAOInterface implDAO;
 
  
  @Autowired
  Status status;
  
  
 
  public StatusEditingController() {
      super();
     
  }
  
  @RequestMapping(value = "/StatusEditingController",  method = RequestMethod.POST)
  public String editType(@RequestParam (NEW_STATUS) String newStatus,@RequestParam (ID) int oldId, ModelMap model) {
  		  
	  
	if ((newStatus==null)||(newStatus.trim().isEmpty())){
				
		List <String> badFields = new ArrayList<>();
		badFields.add(STATUS);
		model.addAttribute(BAD_FIELD, badFields);
		model.addAttribute(CAUSE,EMPTY_FIELDS );
		model.addAttribute(RETURN_PAGE,PAGE );
		
		return	ADD_ERROR_PAGE ;
		
		
	}else {
		try {
			status.setId(oldId);  
			status.setName(newStatus);
			implDAO.updateStatus(status);
						
		} catch (ClassNotFoundException | DAOException e) {
			
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
	}

	return SCSFL_PAGE;
	
  }
}

