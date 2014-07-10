package org.training.issueTracker.web.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.DAO.JDBC.DBImplDAO;
import org.training.issueTracker.service.exceptions.DAOException;


/**
 * Servlet Filter implementation class ChangeUserDataByUserFilter
 */
@WebFilter(urlPatterns = { "/ChangeUserDataByUser" }, servletNames = { "ChangeUserDataByUser" })
public class ChangeUserDataByUserFilter implements Filter {
	

	private final String RETURN_PAGE = "page"; 
	private final String PAGE = "/changeUserDataByUserPage.jsp"; 
	private final String CAUSE = "cause";
	private final String USER = "user";
	private final String EMAIL = "email";
	private final String BAD_FIELD = "badField";
	private final String EMPTY_FIELDS = "emptyField";
	private final String EMAIL_BUSY = "emailBusy";
	private final String DB_PROBLEM = "noDB";
	private final String ADD_ERROR_PAGE = "/errEditingData.jsp";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";	
	private final String FIRST_NAME = "First name";
	private final String LAST_NAME = "Last name";
	private final String EMPTY = "";
	private final String OLD_FIRST_NAME = "choisenFirstName";
	private final String OLD_LAST_NAME = "choisenLastName";
	private final String OLD_EMAIL = "choisenEmail";
	
	private enum EmployeeFields {
		FIRST_NAME,LAST_NAME,EMAIL;
		
	 }

    /**
     * Default constructor. 
     */
    public ChangeUserDataByUserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		String currentEmail = null;
		String newEmail = null;
		Employee user= null;
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpSession session = httpReq.getSession();
		
		
		user = (Employee)session.getAttribute(USER);
		currentEmail = user.getEmail();
		newEmail = (String)request.getParameter(EMAIL);	
		DAOInterface emailSearcher = new DBImplDAO();
	
		
		
		List <String> badFields = new ArrayList<String>();

		List <String> fields = new ArrayList<String>();
		String field = null;
		String parameter = null;
		for (EmployeeFields element : EmployeeFields.values()) {
			parameter = element.toString().toLowerCase();
			

			field =request.getParameter(parameter);
			
			fields.add(field);
			
		}
		
		badFields = isNullOrEmpty(fields);
		
		
			
		if(!badFields.isEmpty()){
			
			request.setAttribute(BAD_FIELD, badFields);
			request.setAttribute(CAUSE,EMPTY_FIELDS );
			request.setAttribute(RETURN_PAGE, PAGE );
			setChoisenEmployeeField(fields,httpReq);
			RequestDispatcher rd = request.getRequestDispatcher(ADD_ERROR_PAGE);
			rd.forward(request, response);
			
		}else{
			if(!(currentEmail.equals(newEmail))){
				try {
					List <Employee> emailList = emailSearcher.getAllEmployees();
					if (!emailList.isEmpty()){
						request.setAttribute(CAUSE, EMAIL_BUSY);
						
						request.setAttribute(BAD_FIELD, badFields);
						
						request.setAttribute(RETURN_PAGE,PAGE );
						setChoisenEmployeeField(fields,httpReq);
						RequestDispatcher rd = request.getRequestDispatcher(ADD_ERROR_PAGE);
						rd.forward(request, response);
						return;
					}
					
				} catch (DAOException | ClassNotFoundException e) {
					request.setAttribute(CAUSE, DB_PROBLEM);
					
					RequestDispatcher rd = request.getServletContext().getRequestDispatcher(DAO_ERROR_PAGE);
					
					rd.forward(request, response);
					return;
				}
			}
				chain.doFilter(request, response);
					
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
private List <String> isNullOrEmpty (List <String> fields){
		
		List <String> badFields = new ArrayList<>();
		String field =null;
		
		for (int i = 0; i < 3; i++) {
		
			field = fields.get(i);
			
			if ((field==null) || (field.trim().isEmpty()) ){
			
		
				badFields.add(getFieldName(i));
			
			}
		}
		
		return badFields;
		
	}

			

	private String getFieldName (int index){
		switch (index) {
	
		case 0:
			return FIRST_NAME;
		case 1:
			return 	LAST_NAME;
		case 2:
			return  EMAIL;
							
		default:
			return  EMPTY;
		
		}		
	}
	
	private void setChoisenEmployeeField(List <String> fields, HttpServletRequest request){
		
		HttpSession session = request.getSession();
				
		session.setAttribute(OLD_FIRST_NAME, fields.get(0));
		session.setAttribute(OLD_LAST_NAME, fields.get(1));
		session.setAttribute(OLD_EMAIL, fields.get(2));
	
	}

}
