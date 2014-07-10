package org.training.issueTracker.web.controllers.issueControllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Attachment;
import org.training.issueTracker.beans.Build;
import org.training.issueTracker.beans.Comment;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.beans.Resolution;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;


@Controller
public class PrepareDataForEditIssueController {

    private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String STATUS_LIST  = "statusList";
	private final String BUILD_LIST  = "buildList";
	private final String MAIL_LIST ="mailList";
	private final String TYPE_LIST = "typeList";
	private final String PRIORITY_LIST = "priorityList";
	private final String PROJECT_NAME = "projectName";
	private final String ID ="id";
	private final String COMMENT_LIST  = "commentList";
	private final String ATTACH_LIST  = "attachList";
	private final String RESOLUTION_LIST = "resolutionList";
	private final String ISSUE = "issue";
	private final String DEFECT_EDIT_PAGE = "defectEditPage";

	
	@Autowired
    DAOInterface implDAO;
   
    @Autowired
    Employee employee  ;
    
    @Autowired
    Issue issue;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDataForEditIssueController() {
        super();
       
    }
    
    @RequestMapping(value = "/PrepareDataForEditIssueController")
    
   
    public String showPage( @RequestParam (ID) int id, ModelMap model, HttpSession httpSession) {
      
		
		try {
			
			Issue issue = implDAO.getIssueById(id);
						
			List<Comment> commentsList = new ArrayList<>(implDAO.getCommentsSortByDate(id));
			
			List<Attachment> attachList = new ArrayList<>(implDAO.getAttachSortByDate(id));
			
			
			List<Employee>  emploeesList = implDAO.getAllEmployees();
			
			
			List<Project>  projectNameList = implDAO.getAllProject();
		
			List<Build> buildList = new ArrayList<>();
			
			buildList = implDAO.getAllBuild();
			
			
			List <Type> typeList = new ArrayList<>();
			
			 typeList = implDAO.getAllTypes();

			 List <Status> statusList = new ArrayList<>();
				
			 statusList = implDAO.getAllStatus();
			
			 
			 List <Priority> priorityList = new ArrayList<>();
				
			 priorityList = implDAO.getAllPriorities();
			 
			 List <Resolution> resolutionList = new ArrayList<>();
				
			 resolutionList = implDAO.getAllResolutions();
	
			 httpSession.setAttribute(COMMENT_LIST, commentsList);
			 httpSession.setAttribute(STATUS_LIST, statusList);
			 httpSession.setAttribute(ATTACH_LIST, attachList);
			 model.addAttribute(BUILD_LIST, buildList);
			 model.addAttribute(MAIL_LIST, emploeesList);
			 model.addAttribute(PROJECT_NAME, projectNameList);
			 httpSession.setAttribute(TYPE_LIST, typeList);
			 httpSession.setAttribute(PRIORITY_LIST, priorityList);
			 httpSession.setAttribute(RESOLUTION_LIST, resolutionList);
			
			 httpSession.setAttribute(ISSUE, issue);
			
		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;
			
		}
		return DEFECT_EDIT_PAGE;
	
	}
}


