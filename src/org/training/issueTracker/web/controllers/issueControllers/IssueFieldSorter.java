package org.training.issueTracker.web.controllers.issueControllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.constants.RoleConst;
import org.training.issueTracker.service.exceptions.DAOException;



@Controller

public class IssueFieldSorter {
  

    private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String USER = "user";
	private final String DEFECT_LIST = "defectList";
	private final String ADMIN_PAGE ="autorizedAdminPage";
	private final String USER_PAGE ="autorizedUserPage";
	private final String START_PAGE ="startPage";
	private final String SORT_BY = "sortColumn";
	
    @Autowired
    DAOInterface implDAO;
   
    @Autowired
    Employee employee  ;
       
  
    public IssueFieldSorter() {
        super();
       
    }
    
    @RequestMapping(value = "/IssueFieldSorter")
    
   
    public String showPage(@RequestParam (SORT_BY) String key, ModelMap model,HttpSession httpSession) {
      
      int capacity = 10;
		
		List<Issue> defectList = new ArrayList<>();
		
		Employee employee = (Employee)httpSession.getAttribute(USER);
		
		try {
			
			if ((employee==null)||(employee.getEmail().trim().isEmpty())){
				defectList = implDAO.getSortedListIssue(key, capacity);
				
			}else {
			
			defectList = implDAO.getSortedListIssue(key, capacity, employee);
			}


		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE ;
			
		} 
						
		model.addAttribute(DEFECT_LIST, defectList);
		
	
		if (employee!=null){
			if(employee.getRole().equals(RoleConst.ADMIN)){	
				return ADMIN_PAGE ;
				

			}
			if(employee.getRole().equals(RoleConst.USER)){	
				return USER_PAGE;
					
			}
			if(employee.getRole().equals(RoleConst.GUEST)){	
				return START_PAGE;
				
			}
			return START_PAGE;
		}else {
			return START_PAGE ;
		}
	}
   
}
  