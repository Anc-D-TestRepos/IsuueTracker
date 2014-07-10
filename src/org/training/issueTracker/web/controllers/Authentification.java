package org.training.issueTracker.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.MD5Hashing;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;


@Controller
public class Authentification {
  

    private final String CAUSE = "cause";
    private final String DAO_ERROR_PAGE = "DAOErrPage";    
    private final String ADMIN = "admin";
	private final String USER = "user";
	private final String DEFECT_LIST = "defectList";
	private final String ADMIN_PAGE = "autorizedAdminPage";
	private final String USER_PAGE =  "autorizedUserPage";
	private final String LOGIN_ERROR_PAGE = "errorLoginPage";
	private final String START_PAGE = "startPage";
    private final String EMPLOYEE = "employee";
    private final String GUEST = "guest";
    private final String ROLE = "role";
    
    
    
    @Autowired
    DAOInterface implDAO;
   
    @Autowired
    Employee baseEmployee  ;
       
  
    public Authentification() {
        super();
       
    }
    
    @RequestMapping(value = "/Authentification")
    
   
    public String showPage(@ModelAttribute(EMPLOYEE) @Valid Employee newEmployee, BindingResult result , ModelMap model,HttpSession httpSession) {
     
  		
      String enteredPassword;
      boolean isValidPass = false;
		
      try {
	      if(result.hasErrors()){
	    	    	  
	    	  int capacity = 10;
	 	              
	          	List<Issue> defectList = implDAO.getListIssues(capacity);
	                  
	              model.addAttribute(ROLE, GUEST);
	              model.addAttribute(DEFECT_LIST, defectList);
	              model.addAttribute(EMPLOYEE, newEmployee);
	      
	    	  return START_PAGE;
	      }
	      
	      MD5Hashing hashKey = new MD5Hashing();
			
		
			enteredPassword = hashKey.getHash(newEmployee.getPassword());
			

			newEmployee.setEmail(newEmployee.getEmail().trim());
			
			baseEmployee = implDAO.getEmployee(newEmployee);
		

			
		} catch (DAOException | SAXException | ClassNotFoundException e ) {
			
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
	
		
		if(baseEmployee != null){
			
			isValidPass = (baseEmployee.getPassword()).equals(enteredPassword);
			
			if (isValidPass){		
				httpSession.setAttribute(USER, baseEmployee);
			}
			
			
			int capacity = 10;
			
			
			List<Issue> defectList = new ArrayList<>();
			
			try {
				
				
				
				defectList = implDAO.getListIssuesbyUser(baseEmployee, capacity);
				
			} catch (DAOException | SAXException | ClassNotFoundException e) {
				
				model.addAttribute(CAUSE, e.getMessage());
				
				return DAO_ERROR_PAGE;
			}
			
			httpSession.setAttribute(DEFECT_LIST, defectList);
			
			
			
			if((ADMIN.equals(baseEmployee.getRole()))&isValidPass){
	
				return ADMIN_PAGE;
				    				
			}else  {
				if((USER.equals(baseEmployee.getRole()))&isValidPass){
					
					return	USER_PAGE;
			    				
				}else {
			
					return LOGIN_ERROR_PAGE;
				    					
				}
			}
		}else {

			return	LOGIN_ERROR_PAGE;
		}

    }	
}
        