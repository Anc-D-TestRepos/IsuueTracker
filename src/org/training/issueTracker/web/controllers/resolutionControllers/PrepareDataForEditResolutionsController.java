package org.training.issueTracker.web.controllers.resolutionControllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Resolution;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;


@Controller
public class PrepareDataForEditResolutionsController {

	private final String OLD_RESOLUTION  = "oldResolution";
	private final String RESOLUTION_EDIT_PAGE = "resolutionEditingPage";
	private final String NAME  = "setName";
	private final String ID  = "setId";
	
	@Autowired
	DAOInterface implDAO;

  
	@Autowired
	Resolution resolution;
  
 
	public PrepareDataForEditResolutionsController() {
      super();
     
	}
  
  @RequestMapping(value = "/PrepareDataForEditResolutionsController")
  
 public String editType(@RequestParam (NAME) String oldName,@RequestParam (ID) int oldId, ModelMap model) {
 

  	resolution.setId(oldId);
  	resolution.setName(oldName);
	
	model.addAttribute(OLD_RESOLUTION, resolution);
		
	return RESOLUTION_EDIT_PAGE;
		
	}
		
}
