package org.training.issueTracker.web.controllers.resolutionControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Resolution;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller


public class PrepareDataForShowResolutionsController {
  /**
   * 
   */

	private final String CAUSE = "cause";
  	private final String DAO_ERROR_PAGE = "DAOErrPage";    
	private final String RESOLUTION_PAGE = "resolutionPage";
	private final String RESOLUTION_LIST = "resolutionList";
  
  
	@Autowired
	DAOInterface implDAO;
 
  
	@Autowired
	Resolution resolution;
  
  
 
	public PrepareDataForShowResolutionsController() {
      super();
     
	}
  
	
	@RequestMapping(value = "/PrepareDataForShowResolutionsController")
    public String prepareType( ModelMap model) {
		

		try {
			List<Resolution> resolutionList = implDAO.getAllResolutions();
				
			model.addAttribute(RESOLUTION_LIST, resolutionList);
	
			
		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
		return	RESOLUTION_PAGE ;
	
	}
}