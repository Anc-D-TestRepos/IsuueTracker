package org.training.issueTracker.web.fileLoadcontrollers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.InputException;
import org.training.issueTracker.service.fileLoader.Loader;

@Controller
public class DownloadController {

	private final String CAUSE = "cause";
	private final String SCSSFUL_ADING_PAGE = "scssfulAddingData";
	private final String RETURN_PAGE = "page";
	private final String EDIT_ERROR_PAGE = "errEditingData";
	private final String FILE_NAME = "fileName";
	private final String APPLICATION_TYPE = "APPLICATION/OCTET-STREAM";
	private final String NAME_HEADER = "Content-Disposition";
	private final String BODY_HEADER = "attachment; filename=\"";

	@Autowired
	DAOInterface implDAO;

	@Autowired
	Employee employee;

	public DownloadController() {
		super();

	}

	@RequestMapping(value = "/DownloadController")
	public String showPage(@RequestParam(FILE_NAME) String fileName,
			ModelMap model, HttpServletRequest req, HttpServletResponse resp) {

		String savePath = req.getServletContext().getRealPath("\\");

		Loader load = new Loader();

		try {

			load.chekFile(fileName, savePath);

			resp.setContentType(APPLICATION_TYPE);

			resp.setHeader(NAME_HEADER, BODY_HEADER + fileName + "\"");

			load.downloadFile(resp, fileName, savePath);

		} catch (InputException e) {

			model.addAttribute(CAUSE, e.getMessage());
			model.addAttribute(RETURN_PAGE, "/defectEditPage.jsp");
			return EDIT_ERROR_PAGE;

		}
		return SCSSFUL_ADING_PAGE;
	}
}
