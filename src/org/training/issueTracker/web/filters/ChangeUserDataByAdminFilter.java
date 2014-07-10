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

/**
 * Servlet Filter implementation class ChangeUserDataByAdminFilter
 */

@WebFilter(urlPatterns = { "/ChangeUserDataByAdmin"},
			servletNames = { "ChangeUserDataByAdmin" })
public class ChangeUserDataByAdminFilter implements Filter {
	private final String RETURN_PAGE = "page";
	private final String PAGE = "/changeUserDataByAdminPage.jsp"; 
	private final String CAUSE = "cause";
	private final String BAD_FIELD = "badField";
	private final String EMPTY_FIELDS = "emptyField";
	private final String ADD_ERROR_PAGE = "/errEditingData.jsp";
	private final String FIRST_NAME = "First name";
	private final String LAST_NAME = "Last name";
	private final String EMAIL = "Email";
	private final String ROLE = "Role";
	private final String EMPTY = "";
	private final String OLD_FIRST_NAME = "choisenFirstName";
	private final String OLD_LAST_NAME = "choisenLastName";
	private final String OLD_EMAIL = "choisenEmail";
	private final String OLD_ROLE = "choisenRole";
	
	
	
	
	private enum EmployeeFields {
		FIRST_NAME,LAST_NAME,EMAIL_EMPLoYEE,ROLE;
		
	 }

    /**
     * Default constructor. 
     */
    public ChangeUserDataByAdminFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest httpReq = (HttpServletRequest)request;

		

		
		
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
			request.setAttribute(RETURN_PAGE,PAGE );
			setChoisenEmployeeField(fields,httpReq);
			RequestDispatcher rd = request.getRequestDispatcher(ADD_ERROR_PAGE);
			rd.forward(request, response);
			
		}else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	
	private List <String> isNullOrEmpty (List <String> fields){
		
		List <String> badFields = new ArrayList<>();
		String field =null;
		
		for (int i = 0; i < 4; i++) {
		
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
		case 3:
			return  ROLE;
					
		default:
			return  EMPTY;
		
		
		}		
	}
	
	private void setChoisenEmployeeField(List <String> fields, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.setAttribute(OLD_FIRST_NAME, fields.get(0));
		session.setAttribute(OLD_LAST_NAME, fields.get(1));
		session.setAttribute(OLD_EMAIL, fields.get(2));
		session.setAttribute(OLD_ROLE, fields.get(3));
		
	}
}
