package org.training.issueTracker.web.controllers.usersControllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;

@Controller
public class PrepareForEditUserDataByAdminController {

	private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "DAOErrPage";
	private final String MAIL_LIST = "mailList";
	private final String USER_EDIT_PAGE = "changeUserDataByAdminPage";

	@Autowired
	DAOInterface implDAO;

	@Autowired
	Employee employee;

	public PrepareForEditUserDataByAdminController() {
		super();

	}

	@RequestMapping(value = "/PrepareForEditUserDataByAdminController")
	public String showPage(ModelMap model, HttpSession httpSession) {

		try {

			List<Employee> emploeesList = implDAO.getAllEmployees();

			model.addAttribute(MAIL_LIST, emploeesList);

		} catch (DAOException | ClassNotFoundException e) {
			model.addAttribute(CAUSE, e.getMessage());
			return DAO_ERROR_PAGE;

		}
		return USER_EDIT_PAGE;

	}

}