package org.training.issueTracker.web.controllers.issueControllers;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Build;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.beans.Resolution;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.CheckerAddingIssueFields;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;



@Controller

public class DefectAddingController {
   
    private final String GET_SUMMARY = "summary";
	private final String GET_DESCRIPTION = "description";
	private final String GET_STATUS = "status";
	private final String GET_TYPE = "type";
	private final String GET_PRIORITY = "priority";
	private final String GET_PROJECT = "project";
	private final String GET_BUILD = "build";
	private final String GET_ASSIGNEE = "assignee";
    private final String CAUSE = "cause";
	private final String ADD_ERROR_PAGE = "errEditingData";
	private final String RETURN_PAGE = "page"; 
	private final String PAGE = "/PrepareDataForAddIssueController";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String BAD_FIELD = "badField";
	private final String USER = "user";
	private final String EMPTY = "";
	private final String EMPTY_FIELDS = "emptyField";
	private final String WRONG_FIELDS = "value fields \"Status\" and \"Assignee\" filled  incorrect";
	private final String SCSSFUL_ADING_PAGE = "scssfulAddingData";
	
	private final int SUMMARY     = 0;
	private final int DESCRIPTION = 1;
	private final int STATUS	  = 2;
	private final int TYPE 		  = 3;
	private final int PRIORITY    = 4;
	private final int PROJECT 	  = 5;
	private final int BUILD 	  = 6;
	private final int ASSIGNEE    = 7;

	private final String OLD_SUMMARY     = "activeSummary";
	private final String OLD_DESCRIPTION = "activeDescription";
	private final String OLD_STATUS		 = "activeStatus";
	private final String OLD_TYPE 		 = "activeType";
	private final String OLD_PRIORITY	 = "activePriority";
	private final String OLD_PROJECT 	 = "activeProject";
	private final String OLD_BUILD 		 = "activeBuild";
	private final String OLD_ASSIGNEE 	 = "activeAssignee";
	
    @Autowired
    DAOInterface implDAO;
   
    @Autowired
    Employee employee  ;
    
    @Autowired
    Issue issue;
    
    @Autowired
    Status status;
    
    @Autowired
    Resolution resolution;
    
    @Autowired
    Type type;
	
    @Autowired
	Priority priority;
	
    @Autowired
	Project project;
	
    @Autowired
	Build build;
    
    
    public DefectAddingController() {
        super();
       
    }
    
    

    @RequestMapping(value = "/DefectAddingController")
    
   
    public String addDefect(@RequestParam (GET_SUMMARY) String newSummary, @RequestParam (GET_DESCRIPTION) String newDescription,
    					   @RequestParam (GET_STATUS) String newStatus, @RequestParam (GET_TYPE) String newType,
    					   @RequestParam (GET_PRIORITY) String newPriority, @RequestParam (GET_PROJECT) String newProject,
    					   @RequestParam (GET_BUILD) String newBuild, @RequestParam (GET_ASSIGNEE) String newAssignee,
    					   ModelMap model,HttpSession httpSession) {
      
      
  
      int statusId = 0;
		
      String description = null;
      boolean hasBadField = false;
      boolean hasCorrectlyField = false;
		
		
      List <String> badFields = new ArrayList<String>();
				
      List <String> fields = new ArrayList<String>();
		
			
      fields.add(newSummary);
      fields.add(newDescription);
      fields.add(newStatus);
      fields.add(newType);
      fields.add(newPriority);
      fields.add(newProject);
      fields.add(newBuild);
      fields.add(newAssignee);
	
		
      badFields = CheckerAddingIssueFields.isFillingCorrectly(fields);
		
      hasBadField = !(badFields.isEmpty());
		
      if (hasBadField){
		
			setChoisenIssueField (fields, httpSession);
		
			model.addAttribute(BAD_FIELD, badFields);
			model.addAttribute(CAUSE,EMPTY_FIELDS );
			model.addAttribute(RETURN_PAGE,PAGE );
			return ADD_ERROR_PAGE;
			
		}
		
		try {
			
			statusId = Integer.parseInt(fields.get(STATUS));
			
			status = implDAO.getStatusById (statusId);
			
			String email = fields.get(ASSIGNEE);
			
			if (email != null){
				employee = implDAO.getEmployee(new Employee (email));
			 }else{
				 employee = implDAO.getEmployee(new Employee (EMPTY));
			 }
			
			hasCorrectlyField = CheckerAddingIssueFields.isCorrectlySatusAndAssignee(status, employee);
			
		

		
			if (hasCorrectlyField){
				issue.setModifiedBy(EMPTY);
				
				resolution = implDAO.getResolutionByName (EMPTY);
				
				issue.setResolution(resolution);
				
				
				issue.setSummary(fields.get(SUMMARY));
				
				description = fields.get(DESCRIPTION);
				
				if (description.length()>250){
					description = description.substring(0, 245)+"...";
					
				}
				issue.setDescription(description);
				
				
				issue.setStatus(status);
				
				type = implDAO.getTypeByName(fields.get(TYPE));
				
				issue.setType(type);
				
				
				priority = implDAO.getPriorityByName(fields.get(PRIORITY));
				issue.setPriority(priority);
				
				project = implDAO.getProjectByName(fields.get(PROJECT));
				issue.setProject(project);
				
				build.setName(newBuild); 
				build.setProject(project);
			
				
				issue.setBuildFound(build.getName());
				
				
				issue.setAssignee(employee);
				
				Employee user = (Employee) httpSession.getAttribute(USER);
				issue.setCreateBy(user.getEmail());
				
			
				implDAO.setIssue(issue);
			
			
				
				
				return SCSSFUL_ADING_PAGE ;
			}else {
					
				setChoisenIssueField (fields, httpSession);
							
				model.addAttribute(CAUSE,WRONG_FIELDS );
				model.addAttribute(RETURN_PAGE,PAGE );
				return ADD_ERROR_PAGE ;
				
			}
	 	} catch (DAOException | ClassNotFoundException | SAXException e) {
	 		model.addAttribute(CAUSE, e.getMessage());
	 		return DAO_ERROR_PAGE;
			
		}
	}
				

	private void setChoisenIssueField (List <String> fields, HttpSession session) {
		session.setAttribute(OLD_SUMMARY, fields.get(SUMMARY));
		session.setAttribute(OLD_DESCRIPTION, fields.get(DESCRIPTION));
		session.setAttribute(OLD_STATUS, fields.get(STATUS));
		session.setAttribute(OLD_TYPE, fields.get(TYPE));
		session.setAttribute(OLD_PRIORITY, fields.get(PRIORITY));
		session.setAttribute(OLD_PROJECT, fields.get(PROJECT));
		session.setAttribute(OLD_BUILD, fields.get(BUILD));
		session.setAttribute(OLD_ASSIGNEE, fields.get(ASSIGNEE));
	
	}
}