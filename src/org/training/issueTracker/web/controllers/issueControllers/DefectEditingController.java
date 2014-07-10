package org.training.issueTracker.web.controllers.issueControllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import org.training.issueTracker.service.DAO.JDBC.CheckerEditingIssueFields;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;

@Controller
public class DefectEditingController {

	private final String ID = "id";
	private final String CREATE_DATE = "create_date";
	private final String CREATE_BY = "create_by";
	private final String MODIFIED_DATE = "modified_date";
	private final String MODIFIED_BY = "modified_by";
	private final String SUMMARY = "summary";
	private final String DESCRIPTION = "description";
	private final String STATUS = "status";
	private final String RESOLUTION = "resolution";
	private final String TYPE = "type";
	private final String PRIORITY = "priority";
	private final String PROJECT = "project";
	private final String BUILD = "build";
	private final String ASSIGNEE = "assignee";
	private final String BAD_FIELD = "badField";
	private final String CAUSE = "cause";
	private final String USER = "user";
	private final String EMPTY = "";
	private final String ISSUE = "issue";
	private final String EMAIL = "email";
	private final String ACT_COMMENT = "activeComment";
	private final String ACT_PROJECT = "activeProject";
	private final String ACT_BUILD = "activeBuild";
	private final String ACT_MAIL = "activeMail";
	private final String RETURN_PAGE = "page";
	private final String EMPTY_FIELDS = "emptyField";
	private final String SCSSFUL_ADING_PAGE = "scssfulAddingData";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String EDIT_ERROR_PAGE = "errEditingData";

	@Autowired
	DAOInterface implDAO;

	@Autowired
	Employee employee;

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
	@Autowired
	Comment comment;

	public DefectEditingController() {
		super();

	}

	@RequestMapping(value = "/DefectEditingController")
	public String addDefect(
			@RequestParam(ID) String id,
			@RequestParam(CREATE_DATE) String create_date,
			@RequestParam(CREATE_BY) String create_by,
			@RequestParam(MODIFIED_DATE) String modified_date,
			@RequestParam(MODIFIED_BY) String modified_by,
			@RequestParam(SUMMARY) String summary,
			@RequestParam(DESCRIPTION) String description,
			@RequestParam(STATUS) String newStatus,
			@RequestParam(value = RESOLUTION, required = false) String newResolution,
			@RequestParam(TYPE) String newType,
			@RequestParam(PRIORITY) String newPriority,
			@RequestParam(PROJECT) String newProject,
			@RequestParam(BUILD) String newBuild,
			@RequestParam(ASSIGNEE) String newAssignee,
			@RequestParam("newComment") String newComment, ModelMap model,
			HttpSession httpSession) {

		List<String> badFields = new ArrayList<>();

		List<String> fields = new ArrayList<>();

		fields.add(id);
		fields.add(create_date);
		fields.add(create_by);
		fields.add(modified_date);
		fields.add(modified_by);
		fields.add(summary);
		fields.add(description);
		fields.add(newStatus);
		fields.add(newResolution);
		fields.add(newType);
		fields.add(newPriority);
		fields.add(newProject);
		fields.add(newBuild);
		fields.add(newAssignee);

		Issue issuefromSession = (Issue) httpSession.getAttribute(ISSUE);

		String activeComment = newComment;
		String activeProject = newProject;
		String activeBuild = newBuild;
		String activeMail = newAssignee;

		try {

			badFields = CheckerEditingIssueFields.isFillingCorrectly(fields);

		} catch (DAOException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;

		}

		try {
			if (!badFields.isEmpty()) {

				List<String> oldFields = new ArrayList<>();
				oldFields.add(summary);
				oldFields.add(description);
				oldFields.add(newStatus);
				oldFields.add(newType);
				oldFields.add(newPriority);
				oldFields.add(newProject);
				oldFields.add(newBuild);
				oldFields.add(newAssignee);

				httpSession.setAttribute(ISSUE,
						setChoisenIssueField(issuefromSession, oldFields));

				httpSession.setAttribute(BAD_FIELD, badFields);
				httpSession.setAttribute(CAUSE, EMPTY_FIELDS);
				httpSession.setAttribute(RETURN_PAGE, "/defectEditPage.jsp");
				httpSession.setAttribute(ACT_COMMENT, activeComment);
				httpSession.setAttribute(ACT_PROJECT, activeProject);
				httpSession.setAttribute(ACT_BUILD, activeBuild);
				httpSession.setAttribute(ACT_MAIL, activeMail);
				return EDIT_ERROR_PAGE;

			}

			description = issuefromSession.getDescription();
			if (description.length() > 250) {
				description = description.substring(0, 245) + "...";

			}
			issuefromSession.setDescription(description);

			issuefromSession.setModifiedBy((String) httpSession
					.getAttribute(EMAIL));

			List<String> oldFields = new ArrayList<>();
			oldFields.add(summary);
			oldFields.add(description);
			oldFields.add(newStatus);
			oldFields.add(newType);
			oldFields.add(newPriority);
			oldFields.add(newProject);
			oldFields.add(newBuild);
			oldFields.add(newAssignee);

			implDAO.replaceIssue(setChoisenIssueField(issuefromSession,
					oldFields));

			if (activeComment.trim().length() > 0) {

				Employee employee = (Employee) httpSession.getAttribute(USER);

				if (employee != null) {
					comment.setAddedBy(employee.getEmail());
				} else {
					comment.setAddedBy(EMPTY);
				}
				comment.setComment(activeComment);
				comment.setIssue(issuefromSession);
				comment.setAddDate(new Date(new java.util.Date().getTime()));
				implDAO.setComment(comment);
			}

		} catch (DAOException | ClassNotFoundException | SAXException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;

		}

		return SCSSFUL_ADING_PAGE;

	}

	private Issue setChoisenIssueField(Issue issue, List<String> fields)
			throws ClassNotFoundException, DAOException, SAXException {
		int statusId = 0;

		issue.setSummary(fields.get(0));

		issue.setDescription(fields.get(1));

		statusId = Integer.parseInt(fields.get(2));
		Status status = implDAO.getStatusById(statusId);
		issue.setStatus(status);

		Type type = implDAO.getTypeByName(fields.get(3));
		issue.setType(type);

		Priority priority = implDAO.getPriorityByName(fields.get(4));
		issue.setPriority(priority);

		Project project = implDAO.getProjectByName(fields.get(5));
		issue.setProject(project);

		issue.setBuildFound(fields.get(6));

		String email = fields.get(7);
		issue.setModifiedBy(email);
		Employee employee = implDAO.getEmployee(new Employee(email));

		issue.setAssignee(employee);

		return issue;
	}
}