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

@Controller
public class ChangeUserDataByUser {

	private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String SCSFL_PAGE = "scssfulAddingData";
	private final String USER = "user";
	private final String MESSAGE = "noDB";
	private final String FIRST_NAME = "first_name";
	private final String LAST_NAME = "last_name";
	private final String EMAIL = "email";

	@Autowired
	DAOInterface implDAO;

	@Autowired
	Employee employee;

	public ChangeUserDataByUser() {
		super();

	}

	@RequestMapping(value = "/ChangeUserDataByUser")
	public String showPage(@RequestParam(FIRST_NAME) String firstName,
			@RequestParam(LAST_NAME) String lastName,
			@RequestParam(EMAIL) String newEmail, ModelMap model,
			HttpSession httpSession) {

		try {
			employee = (Employee) httpSession.getAttribute(USER);


			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmail(newEmail);

			implDAO.replaceUserData(employee);

		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, MESSAGE);
			return DAO_ERROR_PAGE;

		}
		return SCSFL_PAGE;

	}
}
