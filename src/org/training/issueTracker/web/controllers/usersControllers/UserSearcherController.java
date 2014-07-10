package org.training.issueTracker.web.controllers.usersControllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;

@Controller
public class UserSearcherController {

	private final String CAUSE = "cause";
	private final String FINDED_USER_PAGE = "findedUser";
	private final String ADD_ERROR_PAGE = "errEditingData";
	private final String FINDED_USER = "findedUser";
	private final String RETURN_PAGE = "page";
	private final String PAGE = "/findUserPage.jsp";
	private final String EMAIL = "userEmail";
	private final String ACTIVE_EMAIL = "activeEmail";

	@Autowired
	DAOInterface implDAO;

	@Autowired
	Employee employee;

	public UserSearcherController() {
		super();

	}

	@RequestMapping(value = "/UserSearcherController", method = RequestMethod.POST)
	public String showPage(@RequestParam(EMAIL) String email, ModelMap model,
			HttpSession httpSession) {

		if ((email != null) && (!email.trim().isEmpty())) {

			try {

				employee.setEmail(email);

				employee = implDAO.getEmployee(employee);

				model.addAttribute(FINDED_USER, employee);

			} catch (DAOException | ClassNotFoundException | SAXException e) {
				model.addAttribute(CAUSE, e.getMessage());
				model.addAttribute(RETURN_PAGE, PAGE);
				model.addAttribute(ACTIVE_EMAIL, email);

				return ADD_ERROR_PAGE;
			}
		}
		return FINDED_USER_PAGE;

	}

}
