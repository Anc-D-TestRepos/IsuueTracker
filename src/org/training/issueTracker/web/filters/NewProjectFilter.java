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
 * Servlet Filter implementation class NewProjectFilter
 */
@WebFilter(
		urlPatterns = { "/ProjectAddingController" },
		servletNames = { "ProjectAddingController" })
public class NewProjectFilter implements Filter {

	
	private final String NAME = "name";
	private final String DESCRIPTION = "description";
	private final String BUILD = "build";
	private final String MANAGER = "manager";
	private final String CAUSE = "cause";
	private final String RETURN_PAGE = "page"; 	
	private final String PAGE = "/projectAddingPage.jsp";
	private final String  ACT_NAME = "activeName";
	private final String  ACT_DESCR = "activeDescription";
	private final String  EMPTY = "";
	private final String  ACT_MANAGER = "activeManager";	
	private final String BAD_FIELD = "badField";
	private final String EMPTY_FIELDS = "emptyField";
	private final String ADD_ERROR_PAGE = "/errEditingData.jsp";
    /**
     * Default constructor. 
     */
    public NewProjectFilter() {
        
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


		List <String> fields = new ArrayList<>();
		List <String> badFields = new ArrayList<>();
		
		HttpServletRequest httpReq = (HttpServletRequest)request;
		
		fields.add(httpReq.getParameter(NAME));
		fields.add(httpReq.getParameter(DESCRIPTION));
		fields.add(httpReq.getParameter(BUILD));
		fields.add(httpReq.getParameter(MANAGER));
		

		badFields = checkFields(fields); 
		
		if(!badFields.isEmpty()){
			
		
			
			request.setAttribute(BAD_FIELD, badFields);
			request.setAttribute(CAUSE,EMPTY_FIELDS );
			request.setAttribute(RETURN_PAGE,PAGE );
		
			
		
			setChoisenEmployeeField(fields,httpReq);
			
			jump(ADD_ERROR_PAGE,request,response);
			return;
			
		}
	
		
			
			chain.doFilter(request, response);
		
		
	}

	private List<String> checkFields(List<String> fields) {
		
		List <String> badFields = new ArrayList<>();
		String field = null;
		
		for (int i = 0; i < fields.size(); i++){
			field = fields.get(i);
			
			if ( (field==null) || (field.trim().isEmpty())) {
				badFields.add(getFieldName(i));
			}
		}
		return badFields;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	private void jump( String url, ServletRequest req, ServletResponse resp) throws ServletException, IOException{
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		
		rd.forward(req, resp);
		
	}
	

	private void setChoisenEmployeeField(List <String> fields, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		session.setAttribute(ACT_NAME, fields.get(0));
		session.setAttribute(ACT_DESCR, fields.get(1));
		session.setAttribute(ACT_DESCR, fields.get(2));
		session.setAttribute(ACT_MANAGER, fields.get(3));
		
		
	}
	
	;
	private String getFieldName (int index){
		switch (index) {
		case 0:
			return  NAME;
		case 1:
			return 	DESCRIPTION;
		case 2:
			return  BUILD;
		case 3:
			return  MANAGER;
					
		default:
			return  EMPTY;
		
		
		}		
	}
}
