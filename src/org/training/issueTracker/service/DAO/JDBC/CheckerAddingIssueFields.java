package org.training.issueTracker.service.DAO.JDBC;

import java.util.ArrayList;
import java.util.List;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Status;


public class CheckerAddingIssueFields {
	
	private final static String ASSIGNEE = "assigned";
	private final static String NEW = "new";
	
	private enum IssueFieldName {
		SUMMARY,DESCRIPTION,STATUS,TYPE,PRIORITY,PROJECT,BUILD,ASSIGNEE;
		
	 }
	
	
	public CheckerAddingIssueFields() {
		super();
	
	}

	public static List<String> isFillingCorrectly(List <String> fields) {	
	
		List <String> badFields = new ArrayList<String>();
		String field;
		
		for (int i =0; i<fields.size()-1; i++) {
			field = fields.get(i);
		

			if ((field == null) ||(field.isEmpty())){
				
				badFields.add(getFieldName(i));
			
			}
		}
	

		field = fields.get(fields.size()-1);
		
		if (field == null){
			badFields.add(field);
		}
		
		return badFields;
	}

	public static boolean isCorrectlySatusAndAssignee(Status status, Employee employee) {
		
		
		String statusName = status.getName();
		String assigneeEmail = employee.getEmail();
		boolean IsCorrectStatusField = false;
		boolean IsCorrectAssignedField =false;
		boolean result = false;
		
		IsCorrectStatusField =(!assigneeEmail.trim().isEmpty())&( (statusName.toLowerCase()).equals(ASSIGNEE));
		IsCorrectAssignedField =(assigneeEmail.trim().isEmpty())&( (statusName.toLowerCase()).equals(NEW));
		


		result = IsCorrectStatusField|IsCorrectAssignedField;
		
	
		
		return result;
	}
	private static String getFieldName (int index){

		
		
		switch (index) {
			case 0:
				return  IssueFieldName.SUMMARY.toString().toLowerCase();
			case 1:
				return  IssueFieldName.DESCRIPTION.toString().toLowerCase();
			case 2:
				return  IssueFieldName.STATUS.toString().toLowerCase();
			case 3:
				return  IssueFieldName.TYPE.toString().toLowerCase();
			case 4:
				return  IssueFieldName.PRIORITY.toString().toLowerCase();
			case 5:
				return  IssueFieldName.PROJECT.toString().toLowerCase();
			case 6:
				return  IssueFieldName.BUILD.toString().toLowerCase();
			case 7:
				return  IssueFieldName.ASSIGNEE.toString().toLowerCase();
				
			default:
				return  "";
		
		}
	}
}
