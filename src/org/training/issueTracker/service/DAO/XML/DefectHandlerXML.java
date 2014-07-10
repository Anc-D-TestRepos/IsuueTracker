package org.training.issueTracker.service.DAO.XML;


import java.util.ArrayList;
import java.util.List;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.beans.Type;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;



public class DefectHandlerXML  extends DefaultHandler {
	
	private final String NAME = "defect";
	private final int ID= 0;
	private final int PRIORITY = 1;
	private final int ASSIGNEE = 2;
	private final int TYPE = 3;
	private final int STATUS = 4;
	private final int SUMMARY = 5;


	private Issue defect = null;
	private List <Issue> defectsList= null;


	
	
	
	public DefectHandlerXML() {
		super();
		
		defectsList = new ArrayList<Issue>();
	}

	/**
	 * @return the employee
	 */
	public Issue getDefect() {
		return defect;
	}



	/**
	 * @return the defects
	 */
	public List<Issue> getDefectsList() {
		return defectsList;
	}

	public void startElement  (String uri, String  localName, 
			String  qName, Attributes  attributes ){


		if(NAME.equals(qName)){
	
			defect = new Issue();
			
				defect.setId(Integer.parseInt(attributes.getValue(ID)));
				defect.setPriority(new Priority (attributes.getValue(PRIORITY)));
				defect.setAssignee(new Employee (attributes.getValue(ASSIGNEE)));
				defect.setType(new Type (attributes.getValue(TYPE)));
				defect.setStatus(new Status (attributes.getValue(STATUS)));
				defect.setSummary(attributes.getValue(SUMMARY));
				
				
				defectsList.add(defect);
				
		}
	}
}


