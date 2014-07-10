package org.training.issueTracker.web.controllers.issueControllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Build;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller

public class PrepareDataForAddIssueController {
  
    private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String STATUS_LIST  = "statusList";
	private final String BUILD_LIST  = "buildList";
	private final String MAIL_LIST ="mailList";
	private final String TYPE_LIST = "typeList";
	private final String PRIORITY_LIST = "priorityList";
	private final String PROJECT_NAME = "projectName";
	private final String DEFECT_ADD_PAGE = "defectAddingPage";
	
	
	@Autowired
    DAOInterface implDAO;
   
    @Autowired
    Employee employee  ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDataForAddIssueController() {
        super();
       
    }
    
    @RequestMapping(value = "/PrepareDataForAddIssueController")
    
   
    public String prepareData( ModelMap model) {
      
      
  	try {
		List<Employee>  employeesList = implDAO.getAllEmployees();
		
		List<Project>  projectNameList = implDAO.getAllProject();
		
		List<Status> statusList = new ArrayList<>();
		
		statusList= implDAO.getAllStatus();
		
		List<Build> buildList = new ArrayList<>();
		
		buildList = implDAO.getAllBuild();
		
		List <Type> typeList = new ArrayList<>();
		
		typeList = implDAO.getAllTypes();
		
		List <Priority> priorityList = new ArrayList<>();
		
		priorityList = implDAO.getAllPriorities();
		 
		model.addAttribute(STATUS_LIST, statusList);
		model.addAttribute(BUILD_LIST, buildList);
		model.addAttribute(MAIL_LIST, employeesList);
		model.addAttribute(PROJECT_NAME, projectNameList);
		model.addAttribute(TYPE_LIST, typeList);
		model.addAttribute(PRIORITY_LIST, priorityList);
		 
	} catch (DAOException | ClassNotFoundException e) {
		model.addAttribute(CAUSE, e.getMessage());
		return DAO_ERROR_PAGE;

	}
	
  		return DEFECT_ADD_PAGE;

    }
}
