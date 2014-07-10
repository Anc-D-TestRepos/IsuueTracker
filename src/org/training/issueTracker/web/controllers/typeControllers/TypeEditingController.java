package org.training.issueTracker.web.controllers.typeControllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller
public class TypeEditingController {

  private final String CAUSE = "cause";
  private final String TYPE = "Type";
  private final String NEW_TYPE = "newType";
  private final String OLD_ID = "oldId";
  private final String PAGE = "/typeEditingPage.jsp";
  private final String DAO_ERROR_PAGE = "DAOErrPage";    
  private final String ADD_ERROR_PAGE = "errEditingData";
  private final String RETURN_PAGE = "page";
  private final String EMPTY_FIELDS = "emptyField";
  private final String BAD_FIELD = "badField";
  private final String SCSFL_PAGE = "scssfulAddingData";
  
    
  @Autowired
  DAOInterface implDAO;
 
  @Autowired
  Type type;
  
 
  public TypeEditingController() {
      super();
     
  }
  
  @RequestMapping(value = "/TypeEditingController",  method = RequestMethod.POST)
 
  public String editType(@RequestParam (NEW_TYPE) String newType,@RequestParam (OLD_ID) int oldId, ModelMap model) {
  	
	if ((newType==null)||(newType.trim().isEmpty())){
				
		List <String> badFields = new ArrayList<>();
		badFields.add(TYPE);
		model.addAttribute(BAD_FIELD, badFields);
		model.addAttribute(CAUSE,EMPTY_FIELDS );
		model.addAttribute(RETURN_PAGE,PAGE );
		
		return	ADD_ERROR_PAGE ;
		
		
	}else {
		try {
			type.setId(oldId);  
			type.setName(newType);
			implDAO.updateType(type);
			
			
		} catch (ClassNotFoundException | DAOException e) {
		
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
	}
	return SCSFL_PAGE;
	
  }
}