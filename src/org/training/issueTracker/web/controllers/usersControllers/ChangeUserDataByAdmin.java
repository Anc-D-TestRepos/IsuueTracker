package org.training.issueTracker.web.controllers.usersControllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;

@Controller
public class ChangeUserDataByAdmin {

	private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String SCSFL_PAGE = "scssfulAddingData";
	private final String MESSAGE = "noDB";
	private final String FIRST_NAME = "first_name";
	private final String LAST_NAME = "last_name";
	private final String ROLE = "role";
	private final String EMAIL = "email_employee";
	private final String EMAIL_ID = "emailId";

	@Autowired
	DAOInterface implDAO;

	@Autowired
	Employee employee;

	public ChangeUserDataByAdmin() {
		super();

	}

	@RequestMapping(value = "/ChangeUserDataByAdmin")
	public String showPage(@RequestParam(FIRST_NAME) String firstName,
			@RequestParam(LAST_NAME) String lastName,
			@RequestParam(ROLE) String role, @RequestParam(EMAIL) String email,
			@RequestParam(EMAIL_ID) int id, ModelMap model,
			HttpSession httpSession) {

		employee.setEmail(email);

		try {
			employee = implDAO.getEmployee(employee);

			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setRole(role);

			implDAO.replaceUserData(employee);

		} catch (ClassNotFoundException | DAOException | SAXException e) {
			model.addAttribute(CAUSE, MESSAGE);
			return DAO_ERROR_PAGE;
		}

		return SCSFL_PAGE;

	}

}
