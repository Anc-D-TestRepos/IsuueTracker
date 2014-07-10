

package org.training.issueTracker.web.controllers.typeControllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;

@Controller
public class TypeAddingController {

    private final String CAUSE = "cause";
    private final String TYPE = "Type";
    private final String STATUS = "newStatus";
    private final String PAGE = "/typeAddPage.jsp";
    private final String DAO_ERROR_PAGE = "DAOErrPage";    
    private final String ADD_ERROR_PAGE = "errEditingData";
    private final String RETURN_PAGE = "page";
    private final String EMPTY_FIELDS = "emptyField";
    private final String BAD_FIELD = "badField";
	private final String SCSFL_PAGE = "scssfulAddingData";
    
    
    
    @Autowired
    DAOInterface implDAO;
   
    @Autowired
    Employee employee  ;
    
    @Autowired
    Type type;
    
  
    public TypeAddingController() {
        super();
       
    }
    
    @RequestMapping(value = "/TypeAddingController",  method = RequestMethod.POST)
    
   
    public String addType(@RequestParam (STATUS) String newType, ModelMap model,HttpSession httpSession) {

    		
		if ((newType==null)||(newType.trim().isEmpty())){
			
			
			List <String> badFields = new ArrayList<>();
			badFields.add(TYPE);
			model.addAttribute(BAD_FIELD, badFields);
			model.addAttribute(CAUSE,EMPTY_FIELDS );
			model.addAttribute(RETURN_PAGE,PAGE );
			
			return ADD_ERROR_PAGE;
			
			
		}else {
			try {
				 type.setName(newType);
				 implDAO.addType(type);
			} catch (ClassNotFoundException | DAOException e) {
				
				httpSession.setAttribute(CAUSE, e.getMessage());
				return DAO_ERROR_PAGE;
				
			}
		}

		return SCSFL_PAGE;
	
	}
}
        
