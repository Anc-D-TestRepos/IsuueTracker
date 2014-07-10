package org.training.issueTracker.web.fileLoadcontrollers;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Attachment;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;
import org.training.issueTracker.service.exceptions.InputException;
import org.training.issueTracker.service.fileLoader.Loader;



@Controller

public class UploadController {
   
   private final String CAUSE = "cause";   
   private final String USER = "user";
   private final String ISSUE = "issue";
   private final String SCSSFUL_ADING_PAGE = "scssfulAddingData";
   private final String RETURN_PAGE = "page";
   private final String PAGE = "/addFile.jsp"; 
   private final String EDIT_ERROR_PAGE = "errEditingData";
		   
    @Autowired
    DAOInterface implDAO;
   
    @Autowired
    Employee employee  ;
    
    @Autowired   
    Issue issue;
    
    @Autowired
    Attachment attachment;
    
    public UploadController() {
        super();
       
    }
    
    @RequestMapping(value = "/UploadController")
    public String showPage(HttpSession session, ModelMap model,HttpServletRequest req) {
    	
    	String userName = null;
		String fileName = null;
		Loader load = new Loader();
			
		Issue issue = (Issue) session.getAttribute(ISSUE);
						
		employee = (Employee) session.getAttribute(USER);
		
		userName = employee.getEmail();
		
		fileName = load.getFilename();	
		

	
		String savePath = req.getServletContext().getRealPath("\\");

		
			try {
				load.uploadFile(req, savePath);
			
				fileName = load.getFilename();	
						
				attachment.setIssue(issue);
				attachment.setAddDate(new Date(new java.util.Date().getTime()));
				attachment.setFileName(fileName);
				attachment.setAddedBy(userName);
			
				implDAO.addFile(attachment);
	 	
			} catch (InputException | ClassNotFoundException | DAOException e) {
				model.addAttribute(CAUSE, e.getMessage());
				model.addAttribute(RETURN_PAGE,PAGE);
				return EDIT_ERROR_PAGE;
			}
		
		return SCSSFUL_ADING_PAGE;
	}
}
