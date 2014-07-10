package org.training.issueTracker.web.filters;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class NewPasswordFilter
 */
@WebFilter(
		urlPatterns = { "/ChangeUserPassController" }, 
		servletNames = { "ChangeUserPassController"}
		)
public class NewPasswordFilter implements Filter {
	
	private final String NEW_PASS = "newPass";
	private final String CONFIRM_PASS = "confirmPass";
	private final String WRONG_CONFIRM = "wrongConf";
	private final String EMPTY_PASS = "emptyField";
	private final String SHORT_PASS = "shortPass";
	private final String NOT_VALID = "notValid";
	private final String TEMPLATE = "[^A-Za-z0-9,;:.!?@%$]";
	private final String CAUSE = "cause";
	private final String DAO_ERROR_PAGE = "/DAOErrPage.jsp";

    /**
     * Default constructor. 
     */
    public NewPasswordFilter() {
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
		
		String newPassword;
		String confirmPassword;
				
		newPassword = request.getParameter(NEW_PASS);
		confirmPassword = request.getParameter(CONFIRM_PASS);
			
		
		if(!(newPassword.equals(confirmPassword))){
			
			request.setAttribute(CAUSE, WRONG_CONFIRM);
			
			jump(DAO_ERROR_PAGE,request,response);
			return;
			
		}
		
		if((newPassword==null)|(newPassword.isEmpty())){
			
		
			
			request.setAttribute(CAUSE, EMPTY_PASS);
			
			jump(DAO_ERROR_PAGE,request,response);
			return;
			
		}
		if(((newPassword.trim()).length()<5)){
			
		
			
			request.setAttribute(CAUSE, SHORT_PASS);
			
			jump(DAO_ERROR_PAGE,request,response);
			return;
			
		}
		
	 	Pattern wrongSymbol = Pattern.compile(TEMPLATE);
			Matcher result= wrongSymbol.matcher(newPassword);
			
			if (result.find()){ 
				request.setAttribute(CAUSE, NOT_VALID);
				jump(DAO_ERROR_PAGE,request,response);
				return;

			}
				else{
				
			
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	private void jump( String url, ServletRequest req, ServletResponse resp) throws ServletException, IOException{
		
		RequestDispatcher rd = req.getRequestDispatcher(url);
		
		rd.forward(req, resp);
		
	}

}
