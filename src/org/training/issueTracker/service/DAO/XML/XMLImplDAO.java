package org.training.issueTracker.service.DAO.XML;

import java.util.List;

import org.training.issueTracker.beans.Attachment;
import org.training.issueTracker.beans.Build;
import org.training.issueTracker.beans.Comment;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.beans.Resolution;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.service.DAO.DAOInterfaces.DAOInterface;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;



public class XMLImplDAO implements DAOInterface {

	public XMLImplDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public List<Issue> getListIssues(int capacity) throws SAXException {
		DefectSearcherXML defectSearcher = new DefectSearcherXML();
		List<Issue> defectList = defectSearcher.findDefects(capacity);
		

		return defectList;
	}

	@Override
	public Employee getEmployee(Employee user) throws SAXException {
		
		EmployeeSearcherXML employeeSearcher = new EmployeeSearcherXML();
	
		Employee employee  = null;
				
				
		employee = employeeSearcher.findEmployee(user.getEmail());
		return employee;
	}



	@Override
	public List<Issue> getListIssuesbyUser(Employee user, int capacity) throws SAXException {
		DefectSearcherXML defectSearcher  = new DefectSearcherXML();
		List<Issue> defectList = defectSearcher.findDefectsByUser(user.getEmail(), capacity);
		return defectList;
	}

	@Override
	public List<Employee> getAllEmployees() {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	

	@Override
	public List<Build> getAllBuild() {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void setIssue (Issue issue) {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public Issue getIssueById(int id) {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void replaceIssue(Issue issue) {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<Issue> getSortedListIssue(String key, int capacity) throws DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}


	@Override
	public void replaceUserData(Employee user) throws DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<Comment> getCommentsSortByDate(int issueId) {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void setComment(Comment comment) {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public List<Build> getAllBuildVersionByIdProject(int id){
	throw new UnsupportedOperationException("this operation is not supported ");
	}




	@Override
	public List<Project> getAllProject() throws DAOException,
			ClassNotFoundException {	
		throw new UnsupportedOperationException("this operation is not supported ");
	}




	@Override
	public Project getProjectById(int id) throws DAOException, ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<Project> getSubListProject(int offset, int capacity)
			throws DAOException, ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void editProject(Project project, Build build) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}


	@Override
	public List<Type> getAllTypes() throws ClassNotFoundException, DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<Priority> getAllPriorities() throws ClassNotFoundException,
			DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<Resolution> getAllResolutions() throws ClassNotFoundException,
			DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public List<Status> getAllStatus() throws ClassNotFoundException,
			DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void updateType(Type type) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public void updatePriority(Priority priority)
			throws DAOException, ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public void updateResolution(Resolution resolution)
			throws DAOException, ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public void updateStatus(Status status)
			throws DAOException, ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public void addType(Type type) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public void addPriority(Priority priority) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public void addResolution(Resolution resolution) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public void addFile(Attachment file) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}

	@Override
	public List<Attachment> getAttachSortByDate(int issueId)
			throws ClassNotFoundException, DAOException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}

	@Override
	public void addNewUser(Employee employee) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}



	@Override
	public List<Issue> getSortedListIssue(String key, int capacity,
			Employee employee) throws DAOException, ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}



	@Override
	public void addProject(Project project, Build build) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
		
	}



	@Override
	public Project getProjectByName(String name) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}



	@Override
	public List<Integer> getRowsNumberFromProjectTable() throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}



	@Override
	public Build getBuildByNameAndIdProject(String buildName, int projectId)
			throws DAOException, ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}



	@Override
	public Type getTypeByName(String name) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}



	@Override
	public Priority getPriorityByName(String name) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}



	@Override
	public Resolution getResolutionByName(String name) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}



	@Override
	public Status getStatusById(int id) throws DAOException,
			ClassNotFoundException {
		throw new UnsupportedOperationException("this operation is not supported ");
	}





	
}
