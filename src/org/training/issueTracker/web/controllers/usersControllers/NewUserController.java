package org.training.issueTracker.web.controllers.usersControllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.MD5Hashing;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;

@Controller
public class NewUserController {

	private final String CAUSE = "cause";
	private final String ADD_ERROR_PAGE = "errEditingData";
	private final String RETURN_PAGE = "page";
	private final String PAGE = "/addNewUser.jsp";
	private final String EMAIL_BUSY = "emailBusy";
	private final String SCSFL_PAGE = "scssfulAddingData";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String MESSAGE = "noDB";
	private final String OLD_FIRST_NAME = "choisenFirstName";
	private final String OLD_LAST_NAME = "choisenLastName";
	private final String OLD_ROLE = "choisenRole";
	private final String OLD_EMAIL = "choisenEmail";
	private final String EMPLOYEE = "employee";

	@Autowired
	DAOInterface implDAO;

	@Autowired
	Employee employee;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUserController() {
		super();

	}

	@RequestMapping(value = "/NewUserController", method = RequestMethod.POST)
	public String showPage(@ModelAttribute(EMPLOYEE) Employee newEmployee,
			BindingResult result, ModelMap model, HttpSession httpSession) {

		MD5Hashing hashKey = new MD5Hashing();

		try {

			newEmployee.setPassword(hashKey.getHash(newEmployee.getPassword()));

			Employee sameEmployee = implDAO.getEmployee(newEmployee);

			if (sameEmployee == null) {
				implDAO.addNewUser(newEmployee);
			} else {
				model.addAttribute(CAUSE, EMAIL_BUSY);
				model.addAttribute(RETURN_PAGE, PAGE);
				httpSession.setAttribute(OLD_FIRST_NAME,
						newEmployee.getFirstName());
				httpSession.setAttribute(OLD_LAST_NAME,
						newEmployee.getLastName());
				httpSession.setAttribute(OLD_EMAIL, newEmployee.getEmail());
				httpSession.setAttribute(OLD_ROLE, newEmployee.getRole());

				return ADD_ERROR_PAGE;

			}

		} catch (DAOException | ClassNotFoundException | SAXException e) {
			httpSession.setAttribute(CAUSE, MESSAGE);
			return DAO_ERROR_PAGE;

		}
		return SCSFL_PAGE;

	}

}