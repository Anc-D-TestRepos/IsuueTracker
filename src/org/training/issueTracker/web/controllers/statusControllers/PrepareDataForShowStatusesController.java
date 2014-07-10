package org.training.issueTracker.web.controllers.statusControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller
public class PrepareDataForShowStatusesController {


  private final String CAUSE = "cause";
  private final String DAO_ERROR_PAGE = "DAOErrPage";    
  private final String STATUS_LIST  = "statusList";
  private final String SATUS_PAGE = "statusesPage";
    
  @Autowired
  DAOInterface implDAO;
 
  
  @Autowired
  Type type;
  
 
  public PrepareDataForShowStatusesController() {
      super();
     
  }
  
  @RequestMapping(value = "/PrepareDataForShowStatusesController")
  
  public String prepareType( ModelMap model) {
  
	
	try {
		List<Status> statusList = implDAO.getAllStatus();
		
		model.addAttribute(STATUS_LIST, statusList);

		
	} catch (DAOException | ClassNotFoundException e) {
		model.addAttribute(CAUSE, e.getMessage());
		return DAO_ERROR_PAGE;
		
	}
	return	SATUS_PAGE ;
	
  }
}
