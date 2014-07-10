package org.training.issueTracker.service.DAO.DAOInterfaces;

import java.util.List;

import org.training.issueTracker.beans.Attachment;
import org.training.issueTracker.beans.Build;
import org.training.issueTracker.beans.Comment;
import org.training.issueTracker.beans.Priority;
import org.training.issueTracker.beans.Resolution;
import org.training.issueTracker.beans.Status;
import org.training.issueTracker.beans.Type;
import org.training.issueTracker.beans.Issue;
import org.training.issueTracker.beans.Employee;
import org.training.issueTracker.beans.Project;
import org.training.issueTracker.service.exceptions.DAOException;
import org.xml.sax.SAXException;

public interface DAOInterface {
	
	
	List<Issue> getListIssues(int capacity) throws DAOException, SAXException, ClassNotFoundException;
	List<Issue> getListIssuesbyUser(Employee employee, int capacity) throws DAOException, SAXException, ClassNotFoundException;
	Issue getIssueById(int id) throws DAOException, ClassNotFoundException;
	List<Issue> getSortedListIssue (String key, int capacity) throws DAOException, ClassNotFoundException;
	List<Issue> getSortedListIssue (String key, int capacity, Employee employee) throws DAOException, ClassNotFoundException;
	void setIssue(Issue issue) throws DAOException, ClassNotFoundException;
	void replaceIssue(Issue issue) throws DAOException, ClassNotFoundException;
	
	
	
	List<Employee> getAllEmployees() throws DAOException, ClassNotFoundException;
	Employee getEmployee(Employee employee) throws DAOException, SAXException, ClassNotFoundException;
	void replaceUserData(Employee user) throws DAOException, ClassNotFoundException;
	void addNewUser(Employee employee) throws DAOException, ClassNotFoundException;
	
	
	
	
	List<Project> getAllProject() throws DAOException, ClassNotFoundException;
	List<Project> getSubListProject(int offset, int capacity)throws DAOException, ClassNotFoundException;
	Project getProjectById(int id) throws DAOException, ClassNotFoundException;
	void addProject (Project project, Build build) throws DAOException, ClassNotFoundException;
	void editProject (Project project, Build build) throws DAOException, ClassNotFoundException;
	Project  getProjectByName(String name) throws DAOException,ClassNotFoundException;
	
	List<Integer> getRowsNumberFromProjectTable() throws DAOException, ClassNotFoundException ;
		
	
	List<Build> getAllBuildVersionByIdProject(int id) throws DAOException, ClassNotFoundException;
	List<Build> getAllBuild() throws DAOException, ClassNotFoundException;
	Build  getBuildByNameAndIdProject(String buildName,int projectId) throws DAOException,ClassNotFoundException;
	
	List <Comment> getCommentsSortByDate(int issueId) throws ClassNotFoundException, DAOException;
	void setComment(Comment comment) throws ClassNotFoundException, DAOException;
	
	List <Type> getAllTypes () throws ClassNotFoundException, DAOException;
	void updateType(Type type) throws DAOException, ClassNotFoundException;
	void addType(Type type) throws DAOException, ClassNotFoundException;
	Type getTypeByName(String name) throws DAOException,ClassNotFoundException;
	
	List <Priority> getAllPriorities () throws ClassNotFoundException, DAOException;
	void updatePriority(Priority priority) throws DAOException, ClassNotFoundException;
	void addPriority(Priority priority) throws DAOException, ClassNotFoundException;
	Priority getPriorityByName(String name) throws DAOException,ClassNotFoundException;
	
	List <Resolution> getAllResolutions () throws ClassNotFoundException, DAOException;
	void updateResolution(Resolution resolution) throws DAOException, ClassNotFoundException;
	void addResolution(Resolution resolution) throws DAOException, ClassNotFoundException;
	Resolution getResolutionByName (String name) throws DAOException, ClassNotFoundException;
	
	List <Status> getAllStatus () throws ClassNotFoundException, DAOException;
	void updateStatus(Status  status) throws DAOException, ClassNotFoundException;
	Status getStatusById(int id) throws DAOException, ClassNotFoundException;
	
	
	void addFile (Attachment file) throws DAOException, ClassNotFoundException;
	List <Attachment> getAttachSortByDate(int issueId) throws ClassNotFoundException, DAOException;
	
	
	
}
