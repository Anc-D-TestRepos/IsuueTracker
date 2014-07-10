package org.training.issueTracker.tags;

import java.io.IOException;

import org.apache.log4j.Logger;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;

public class ShortOutTag extends TagSupport implements DynamicAttributes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  final String IO_ERR = " Input\\Output error in jstl tag <ShortOutTag> ";
	private Logger logger= Logger.getLogger(ShortOutTag.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	
	
	
	@Override
    public int doStartTag() throws JspException {
		String text =null;
		try {
			for ( String name : map.keySet()){
								
			if ((name!=null) ) {
				text = ((String)map.get( name ));
				if (text.length()>100){
					text = text.substring(0, 99) + "...";
				}
			pageContext.getOut().print(text);}
			
			}
		} catch (IOException e) {
			logger.error(IO_ERR + e.getMessage());
		}
		
		return SKIP_BODY;
		
	}

	
	public void setDynamicAttribute(String arg0, String name, Object value)
			throws JspException {
		map.put(name, value);
		
	}
	
	
	

}
