package org.training.issueTracker.web.controllers.typeControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;


@Controller
public class PrepareDataForEditTypeController {

	private final String OLD_TYPE  = "oldType";
	private final String TYPE_EDIT_PAGE = "typeEditingPage";
	private final String NAME  = "setName";
	private final String ID  = "setId";
	@Autowired
	DAOInterface implDAO;

  
	@Autowired
	Type oldType;
  
 
	public PrepareDataForEditTypeController() {
      super();
     
	}
  
  @RequestMapping(value = "/PrepareDataForEditTypeController")
  
 public String editType(@RequestParam (NAME) String oldName,@RequestParam (ID) int oldId, ModelMap model) {
  	

	oldType.setId(oldId);
	oldType.setName(oldName);
	
	model.addAttribute(OLD_TYPE, oldType);
		
	return TYPE_EDIT_PAGE;
		
	}
		
}

