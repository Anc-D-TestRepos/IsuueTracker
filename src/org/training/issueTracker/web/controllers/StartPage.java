
package org.training.issueTracker.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;



@Controller

public class StartPage {
   
   
    private final String  DEFECT_LIST = "defectList";
    private final String  START_PAGE = "startPage";
    private final String  EMPLOYEE = "employee";
    private final String  ROLE = "role";
    private final String  GUEST = "guest";
    private final String CAUSE = "cause";
    private final String DAO_ERROR_PAGE = "DAOErrPage";    
    
    @Autowired
    DAOInterface implDAO;
    
    @Autowired
    Employee employee;
       
  
    public StartPage() {
        super();
       
    }
        
    @Autowired
    public void setTemplate(DBImplDAO implDAO) {
    	this.implDAO = implDAO;
    }
    
    @RequestMapping(value = "/StartPage")
    public String showStartPage(ModelMap model) {
        
        int capacity = 10;
        
        try {
            
        	List<Issue> defectList = implDAO.getListIssues(capacity);
                            
            model.addAttribute(ROLE, GUEST);
            model.addAttribute(DEFECT_LIST, defectList);
            model.addAttribute(EMPLOYEE, employee);
    
            
            return START_PAGE;
        
        } catch (ClassNotFoundException | DAOException | SAXException e) {
            
            model.addAttribute(CAUSE, e.getMessage());
            
            return DAO_ERROR_PAGE;
        } 
    }
        
}
