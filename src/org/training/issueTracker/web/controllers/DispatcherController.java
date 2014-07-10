package org.training.issueTracker.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;




@Controller

public class DispatcherController {
	
	private final String ADMIN = "admin";
	private final String USER = "user";
	private final String ADMIN_PAGE = "autorizedAdminPage";
	private final String USER_PAGE =  "autorizedUserPage";
	private final String START_PAGE = "startPage";
	
	@Autowired
    DAOInterface implDAO;
    
	public DispatcherController() {
	        super();
	       
	}	   
	   
   @RequestMapping(value = "/DispatcherController")
    
    public String getPage( HttpSession httpSession ) {
	 
	 
   		Employee employee = (Employee)httpSession.getAttribute(USER);
   		   	
   		if (employee!=null){
   			
   			if (employee.getRole().equals(ADMIN)){
   				   
   				return ADMIN_PAGE;
   				
   			}
   			if (employee.getRole().equals(USER)){
   				
   				return USER_PAGE;
   				
   			}else {
   				
   				return START_PAGE;   				
   			}
   		}else {
   			return START_PAGE;
   		}
   	}
}