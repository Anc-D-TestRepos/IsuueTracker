package org.training.issueTracker.web.controllers.priorityControllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
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
public class PriorityAddingController {


    private final String CAUSE = "cause"; 
    private final String SATUS = "newStatus";
    private final String COLOR = "black";
    private final String RESOLUTION = "Resolution";
    private final String DAO_ERROR_PAGE = "DAOErrPage";    
    private final String ADD_ERROR_PAGE = "errEditingData";
    private final String RETURN_PAGE = "page";
    private final String PAGE = "/resolutionAddPage.jsp";
    private final String EMPTY_FIELDS = "emptyField";
    private final String BAD_FIELD = "badField";
	private final String SCSFL_PAGE = "scssfulAddingData";
    
    
    
    @Autowired
    DAOInterface implDAO;
   
      
    @Autowired
    Priority priority;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PriorityAddingController() {
        super();
       
    }
    
    @RequestMapping(value = "/PriorityAddingController",  method = RequestMethod.POST)
    
   
    public String addType(@RequestParam (SATUS) String newPriority, ModelMap model,HttpSession httpSession) {

    	
		if ((newPriority==null)||(newPriority.trim().isEmpty())){
			
			
			List <String> badFields = new ArrayList<>();
			badFields.add(RESOLUTION);
			model.addAttribute(BAD_FIELD, badFields);
			model.addAttribute(CAUSE,EMPTY_FIELDS );
			model.addAttribute(RETURN_PAGE,PAGE );
			
			return ADD_ERROR_PAGE;
			
			
		}else {
			try {
				priority.setName(newPriority);
				priority.setColor(COLOR);
				implDAO.addPriority(priority);
			} catch (ClassNotFoundException | DAOException e) {
				
				httpSession.setAttribute(CAUSE, e.getMessage());
				return DAO_ERROR_PAGE;
				
			}
		}

		return SCSFL_PAGE;
	
	}
}
