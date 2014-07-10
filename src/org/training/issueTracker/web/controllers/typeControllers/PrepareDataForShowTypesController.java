package org.training.issueTracker.web.controllers.typeControllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller
public class PrepareDataForShowTypesController {


	private final String CAUSE = "cause";
  	private final String DAO_ERROR_PAGE = "DAOErrPage";    
	private final String TYPE_PAGE = "typePage";
	private final String TYPE_LIST  = "typeList";
  
  
	@Autowired
	DAOInterface implDAO;
   
	@Autowired
	Type type;
   
	public PrepareDataForShowTypesController() {
      super();
     
	}
  	
	@RequestMapping(value = "/PrepareDataForShowTypesController")
    public String prepareType( ModelMap model) {
		
		try {
			List<Type> typeList = implDAO.getAllTypes();
			
			model.addAttribute(TYPE_LIST, typeList);
				
		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
		return	TYPE_PAGE ;
	
	}
}