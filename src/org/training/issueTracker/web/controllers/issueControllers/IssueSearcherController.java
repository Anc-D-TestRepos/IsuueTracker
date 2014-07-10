package org.training.issueTracker.web.controllers.issueControllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;



@Controller

public class IssueSearcherController {
   
    private final String CAUSE = "cause";
    private final String ISSUE = "issue";
    private final String ACTIVE_ID = "activeIssueId";
    private final String MESSAGE = "id should be only digit";
    private final String FINDED_ISSUE_PAGE = "findedIssue";
	private final String ADD_ERROR_PAGE = "errEditingData";
	private final String RETURN_PAGE = "page"; 
	private final String PAGE = "/findIssuePage.jsp";
    
    
    @Autowired
    DAOInterface implDAO;
   
    @Autowired
    Issue issue;
       
  
    public IssueSearcherController() {
        super();
       
    }
    
    @RequestMapping(value = "/IssueSearcherController")
    
    public String showPage(@RequestParam ("issueId") String issueId,ModelMap model,HttpSession httpSession) {
     
	  	int id= 0;
	
		if ((issueId!= null)&& (!issueId.trim().isEmpty())) {
			
			try {
				
					id = Integer.parseInt(issueId);
			
					issue = implDAO.getIssueById(id);
				
					model.addAttribute(ISSUE, issue);
							
							
			}  catch( NumberFormatException  e){
				model.addAttribute(CAUSE,MESSAGE );
				model.addAttribute(RETURN_PAGE,PAGE );
				model.addAttribute(ACTIVE_ID, issueId);
				
				return ADD_ERROR_PAGE;
				
		
			}catch (DAOException | ClassNotFoundException  e) {
				model.addAttribute(CAUSE,e.getMessage() );
				model.addAttribute(RETURN_PAGE,PAGE );
				model.addAttribute(ACTIVE_ID, issueId);
				
				return ADD_ERROR_PAGE;
				
			}
		}
		return FINDED_ISSUE_PAGE;
 	    	
    }
}

