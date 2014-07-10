package org.training.issueTracker.web.controllers.usersControllers;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.MD5Hashing;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;


@Controller
public class ChangeUserPassController {

    private final String CAUSE = "cause";
	private final String ERROR_PAGE = "errAddingData";
	private final String SCSFL_PAGE = "scssfulAddingData";
	private final String USER = "user";
	private final String NEW_PASS = "newPass";
	private final String CONFRM_PASS = "confirmPass";


	
    @Autowired
    DAOInterface implDAO;
   
    @Autowired
    Employee employee  ;
       
  
    public ChangeUserPassController() {
        super();
       
    }
    
    @RequestMapping(value = "/ChangeUserPassController")
    
   
    public String showPage(@RequestParam (NEW_PASS) String newPass, @RequestParam (CONFRM_PASS) String confirmPass,
    					    ModelMap model,HttpSession httpSession) {
     

      MD5Hashing hashKey = new MD5Hashing();
      String enteredPassword;
	
    

      employee = (Employee) httpSession.getAttribute(USER);
   
		try {
			enteredPassword = hashKey.getHash(confirmPass);
			
			employee = implDAO.getEmployee(employee);
			
			employee.setPassword(enteredPassword);
			
			
				implDAO.replaceUserData(employee);
			} catch (ClassNotFoundException | DAOException | SAXException e) {
				model.addAttribute(CAUSE, e.getMessage());
				return ERROR_PAGE;
			}
			
			return SCSFL_PAGE;
			
	
    }
}
