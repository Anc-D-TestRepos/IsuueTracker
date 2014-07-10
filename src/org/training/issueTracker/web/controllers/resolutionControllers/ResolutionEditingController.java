package org.training.issueTracker.web.controllers.resolutionControllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Resolution;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;

@Controller
public class ResolutionEditingController {

	private final String CAUSE = "cause";
	private final String OLD_ID = "oldId";
	private final String RESOLUTION = "Resolution";
	private final String NEW_RESOLUTION = "newResolution";
	private final String RETURN_PAGE = "page";
	private final String PAGE = "/resolutionEditingPage.jsp";
	private final String BAD_FIELD = "badField";
	private final String ADD_ERROR_PAGE = "errEditingData";
	private final String EMPTY_FIELDS = "emptyField";
	private final String SCSFL_PAGE = "scssfulAddingData";
	private final String DAO_ERROR_PAGE = "DAOErrPage";

	@Autowired
	DAOInterface implDAO;

	@Autowired
	Resolution resolution;

	public ResolutionEditingController() {
		super();

	}

	@RequestMapping(value = "/ResolutionEditingController", method = RequestMethod.POST)
	public String editType(@RequestParam(NEW_RESOLUTION) String newResolution,
			@RequestParam(OLD_ID) int oldId, ModelMap model) {

		if ((newResolution == null) || (newResolution.trim().isEmpty())) {

			List<String> badFields = new ArrayList<>();
			badFields.add(RESOLUTION);
			model.addAttribute(BAD_FIELD, badFields);
			model.addAttribute(CAUSE, EMPTY_FIELDS);
			model.addAttribute(RETURN_PAGE, PAGE);

			return ADD_ERROR_PAGE;

		} else {
			try {

				resolution.setId(oldId);
				resolution.setName(newResolution);
				implDAO.updateResolution(resolution);

			} catch (ClassNotFoundException | DAOException e) {

				model.addAttribute(CAUSE, e.getMessage());
				return DAO_ERROR_PAGE;

			}
		}

		return SCSFL_PAGE;

	}
}
