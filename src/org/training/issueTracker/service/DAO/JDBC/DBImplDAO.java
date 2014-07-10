package org.training.issueTracker.service.DAO.JDBC;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
import org.training.issueTracker.utils.HibernateUtil;
import org.apache.log4j.Logger;

@Repository
public class DBImplDAO implements DAOInterface {
	private final String HYB_ERR = " Problem with Hybernate or DataBase access - ";
	private final String EMAIL = "email";	
	private final String NAME = "name";
	private final String EMPTY = "";
	private final String STATUS = "status";
	private final String SORT_BY_STATUS= "ORDER BY status ASC";
	private final String TYPE = "type";
	private final String SORT_BY_TYPE= "ORDER BY type ASC";
	private final String ASSIGNEE = "assignee";
	private final String SORT_BY_ASSIGNEE= "ORDER BY assignee ASC";
	private final String PRIORITY = "priority";
	private final String SORT_BY_PRIORITY= "ORDER BY priority ASC";
	private final String SUMMARY = "summary";
	private final String SORT_BY_SUMMARY= "ORDER BY summary ASC";
	private final String ID = "id";	
	private final String SORT_BY_ID= "ORDER BY id ASC";
	private final String SLC_COUN_ROWS_ISSUE = "select count (*) from Issue where assignee.id =:id ";
	private final String SLC_LIST_ISSUE_BY_USER = "from Issue where assignee =:id ORDER BY id ASC";
	private final String SLC_BUILD_BY_NAME = "from Build where id =:id and name=:name";
	private final String SLC_ISSUE_BY_ID = " from Issue where id =:id ";
	private final String SLC_FROM_ISSUE = "from Issue where assignee =:id ";
	private final String SLC_FROM_COMMENT = "from Comment where idComment =:id ORDER BY addDate ASC";
	private final String SLC_FROM_ATTACH = "from Attachment where idAttachment =:id ORDER BY addDate ASC";
	private Logger logger = Logger.getLogger(DBImplDAO.class);

	public DBImplDAO() {
		super();

	}

	
	@Transactional
	@Override
	public List<Issue> getListIssues(int capacity) throws DAOException,
			ClassNotFoundException {
		int offset = 0;
		Integer rows = null;
		List<Issue> issues;
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();

		Object result = session.createCriteria(Issue.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		rows = Integer.parseInt(result.toString());

		if (rows > capacity) {
			offset = rows - capacity;

			issues = session.createCriteria(Issue.class).setFirstResult(offset)
					.setMaxResults(capacity).addOrder(Order.asc(ID)).list();
		} else {
			issues = session.createCriteria(Issue.class)
					.addOrder(Order.asc(ID)).list();
		}

		if (session != null && session.isOpen()) {
			session.close();
		}

		return issues;

	}

	@Transactional
	@Override
	public Employee getEmployee(Employee employee) throws DAOException,
			ClassNotFoundException {
		Session session = null;
		Employee findedEmployee = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Object result = session.createCriteria(Employee.class)
				.add(Restrictions.eq(EMAIL, employee.getEmail()))
				.uniqueResult();

		findedEmployee = (Employee) result;

		if (session != null && session.isOpen()) {
			session.close();
		}

		return findedEmployee;

	}

	@Transactional
	@Override
	public List<Issue> getListIssuesbyUser(Employee employee, int capacity)
			throws DAOException, ClassNotFoundException {
		int offset = 0;
		Integer rows = null;
		List<Issue> issues;
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();

		Query query1 = session
				.createQuery(SLC_COUN_ROWS_ISSUE);

		query1.setInteger(ID, employee.getId());

		Object result = query1.uniqueResult();

		rows = Integer.parseInt(result.toString());

		if (rows > capacity) {
			offset = rows - capacity;

			offset = rows - capacity;
			Query query = session.createQuery(
					SLC_LIST_ISSUE_BY_USER)
					.setFirstResult(offset);

			query.setInteger(ID, employee.getId());

			issues = query.list();

		} else {

			Query query = session
					.createQuery(SLC_LIST_ISSUE_BY_USER);

			query.setInteger(ID, employee.getId());

			issues = query.list();
		}

		if (session != null && session.isOpen()) {
			session.close();
		}

		return issues;
	}

	@Transactional
	@Override
	public List<Employee> getAllEmployees() throws DAOException,
			ClassNotFoundException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		List<Employee> employees = session.createCriteria(Employee.class)
				.add(Restrictions.isNotNull(EMAIL)).list();

		if (session != null && session.isOpen()) {
			session.close();
		}

		return employees;
	}

	@Transactional
	@Override
	public List<Build> getAllBuild() throws DAOException,
			ClassNotFoundException {
		Session session = null;
		List<Build> builds = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			builds = session.createCriteria(Build.class).list();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return builds;
	}

	@Transactional
	@Override
	public List<Build> getAllBuildVersionByIdProject(int id)
			throws DAOException, ClassNotFoundException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		List<Build> builds = session.createCriteria(Build.class)
				.add(Restrictions.eq(ID, id)).list();

		if (session != null && session.isOpen()) {
			session.close();
		}
		return builds;

	}

	@Transactional
	@Override
	public Build getBuildByNameAndIdProject(String buildName, int projectId)
			throws DAOException, ClassNotFoundException {

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery(SLC_BUILD_BY_NAME);

		query.setInteger(ID, projectId);
		query.setString(NAME, buildName);

		Object result = query.uniqueResult();

		if (session != null && session.isOpen()) {
			session.close();
		}
		return (Build) result;
	}

	@Transactional
	@Override
	public void setIssue(Issue issue) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {

			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			issue.setCreateDate(new Date(new java.util.Date().getTime()));
			session.save(issue);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

	}

	@Transactional
	@Override
	public Issue getIssueById(int id) throws DAOException,
			ClassNotFoundException {

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();

		Query query = session.createQuery(SLC_ISSUE_BY_ID);

		query.setInteger(ID, id);

		Issue result = (Issue) query.uniqueResult();

		if (session != null && session.isOpen()) {
			session.close();
		}
		return result;

	}

	@Transactional
	@Override
	public void replaceIssue(Issue issue) throws DAOException,
			ClassNotFoundException {

		Session session = null;

		try {
			issue.setModifiedDate(new Date(new java.util.Date().getTime()));
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(issue);

			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Transactional
	@Override
	public List<Issue> getSortedListIssue(String key, int capacity)
			throws DAOException, ClassNotFoundException {

		int offset = 0;
		Integer rows = null;
		List<Issue> issues;
		Session session = null;

		session = HibernateUtil.getSessionFactory().openSession();

		Object result = session.createCriteria(Issue.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		rows = Integer.parseInt(result.toString());

		if (rows > capacity) {
			offset = rows - capacity;

			issues = session.createCriteria(Issue.class)
					.addOrder(Order.asc(key))
					.add(Restrictions.between(ID, offset, offset + 10))
					.list();

		} else {
			issues = session.createCriteria(Issue.class)
					.addOrder(Order.asc(key)).list();
		}

		if (session != null && session.isOpen()) {
			session.close();
		}

		return issues;

	}

	@Transactional
	@Override
	public List<Issue> getSortedListIssue(String key, int capacity,
			Employee employee ) throws DAOException,
			ClassNotFoundException {

		int offset = 0;
		Integer rows = null;
		List<Issue> issues;
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();

		Query query1 = session
				.createQuery(SLC_COUN_ROWS_ISSUE);

		query1.setInteger(ID, employee.getId());

		Object result = query1.uniqueResult();
		String sortKey = EMPTY;

		rows = Integer.parseInt(result.toString());

		switch (key) {
		case STATUS:
			sortKey = SORT_BY_STATUS;
			break;
		case TYPE:
			sortKey = SORT_BY_TYPE;
			break;
		case ASSIGNEE:
			sortKey = SORT_BY_ASSIGNEE;
			break;
		case PRIORITY:
			sortKey = SORT_BY_PRIORITY;
			break;
		case SUMMARY:
			sortKey = SORT_BY_SUMMARY;
			break;
		case ID:
			sortKey = SORT_BY_ID;
			break;

		}

		if (rows > capacity) {
			offset = rows - capacity;

			offset = rows - capacity;
			Query query = session.createQuery(
					SLC_FROM_ISSUE + sortKey)
					.setFirstResult(offset);

			query.setInteger(ID, employee.getId());

			issues = query.list();

		} else {

			Query query = session.createQuery(SLC_FROM_ISSUE
					+ sortKey);

			query.setInteger(ID, employee.getId());

			issues = query.list();
		}

		for (Issue issue : issues) {

		}

		if (session != null && session.isOpen()) {
			session.close();
		}

		return issues;
	}

	@Transactional
	@Override
	public void replaceUserData(Employee user) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(user);

			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Transactional
	@Override
	public List<Comment> getCommentsSortByDate(int issueId)
			throws ClassNotFoundException, DAOException {

		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();

		Query query = session
				.createQuery(SLC_FROM_COMMENT);

		query.setInteger(ID, issueId);

		List<Comment> comments = query.list();

		for (Comment comment : comments) {

		}

		if (session != null && session.isOpen()) {
			session.close();
		}
		return comments;
	}

	@Transactional
	@Override
	public void setComment(Comment comment) throws ClassNotFoundException,
			DAOException {
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
	}

	@Transactional
	@Override
	public void addFile(Attachment file) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(file);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Transactional
	@Override
	public void addProject(Project project, Build build) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(project);
			build.setProject(project);
			session.save(build);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}

	}

	@Transactional
	@Override
	public List<Project> getAllProject() throws DAOException,
			ClassNotFoundException {
		Session session = null;
		List<Project> projects = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			projects = session.createCriteria(Project.class).list();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return projects;
	}

	@Transactional
	@Override
	public Project getProjectById(int id) throws DAOException,
			ClassNotFoundException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();

		Object result = session.createCriteria(Project.class)
				.add(Restrictions.eq(ID, id)).uniqueResult();
		Project proj = (Project) result;

		if (!proj.getBuilds().isEmpty()) {
			Set<Build> build = (Set<Build>) proj.getBuilds();
			for (Build projectB : build) {

			}
		}

		if (session != null && session.isOpen()) {
			session.close();
		}
		return proj;
	}

	@Transactional
	@Override
	public Project getProjectByName(String name) throws DAOException,
			ClassNotFoundException {
		Session session = null;
		Project findedProject = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Object result = session.createCriteria(Project.class)
				.add(Restrictions.eq(NAME, name)).uniqueResult();

		findedProject = (Project) result;

		if (session != null && session.isOpen()) {
			session.close();
		}

		return findedProject;
	}

	public List<Integer> getRowsNumberFromProjectTable() throws DAOException,
			ClassNotFoundException {

		List<Integer> rowsNumberList = new ArrayList<Integer>();
		Integer rows = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Object result = session.createCriteria(Project.class)
					.setProjection(Projections.rowCount()).uniqueResult();

			rows = Integer.parseInt(result.toString());

			for (int i = 1; rows > 1; i++) {

				rows -= 10;
				rowsNumberList.add(new Integer(i));

			}
		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return rowsNumberList;
	}

	@Transactional
	@Override
	public List<Project> getSubListProject(int offset, int capacity)
			throws DAOException, ClassNotFoundException {
		List<Project> projects = new ArrayList<>();
		Session session = null;
		try {

			session = HibernateUtil.getSessionFactory().openSession();

			projects = session.createCriteria(Project.class)
					.setFirstResult(offset).setMaxResults(capacity).list();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return projects;
	}

	@Transactional
	@Override
	public void editProject(Project project, Build build) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(project);

			build.setProject(project);
			session.save(build);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Transactional
	@Override
	public List<Priority> getAllPriorities() throws ClassNotFoundException,
			DAOException {
		Session session = null;
		List<Priority> priorities = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			priorities = session.createCriteria(Priority.class).list();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return priorities;
	}

	@Transactional
	@Override
	public void addPriority(Priority priority) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(priority);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}

		}
	}

	@Transactional
	@Override
	public Priority getPriorityByName(String name) throws DAOException,
			ClassNotFoundException {
		Session session = null;
		Priority findedPriority = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Object result = session.createCriteria(Priority.class)
				.add(Restrictions.eq(NAME, name)).uniqueResult();

		findedPriority = (Priority) result;

		if (session != null && session.isOpen()) {
			session.close();
		}

		return findedPriority;
	}

	@Transactional
	@Override
	public void updatePriority(Priority priority) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(priority);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Transactional
	@Override
	public void updateResolution(Resolution resolution) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(resolution);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Transactional
	@Override
	public void addResolution(Resolution resolution) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(resolution);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Transactional
	@Override
	public List<Resolution> getAllResolutions() throws ClassNotFoundException,
			DAOException {
		Session session = null;
		List<Resolution> resolutions = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			resolutions = session.createCriteria(Resolution.class).list();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return resolutions;
	}

	@Transactional
	@Override
	public Resolution getResolutionByName(String name) throws DAOException,
			ClassNotFoundException {
		Session session = null;
		Resolution findedResolution = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Object result = session.createCriteria(Resolution.class)
				.add(Restrictions.eq(NAME, name)).uniqueResult();

		findedResolution = (Resolution) result;

		if (session != null && session.isOpen()) {
			session.close();
		}

		return findedResolution;
	}

	@Transactional
	@Override
	public List<Status> getAllStatus() throws ClassNotFoundException,
			DAOException {
		Session session = null;
		List<Status> statuses = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			statuses = session.createCriteria(Status.class).list();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return statuses;
	}

	@Transactional
	@Override
	public Status getStatusById(int id) throws DAOException,
			ClassNotFoundException {
		Session session = null;
		Status findedStatus = null;

		session = HibernateUtil.getSessionFactory().openSession();
		Object result = session.createCriteria(Status.class)
				.add(Restrictions.eq(ID, id)).uniqueResult();

		findedStatus = (Status) result;

		if (session != null && session.isOpen()) {
			session.close();
		}

		return findedStatus;
	}

	@Transactional
	@Override
	public void updateStatus(Status status) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(status);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Transactional
	@Override
	public void addType(Type type) throws DAOException, ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(type);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Transactional
	@Override
	public List<Type> getAllTypes() throws ClassNotFoundException, DAOException {
		Session session = null;
		List<Type> types = new ArrayList<>();
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			types = session.createCriteria(Type.class).list();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return types;
	}

	@Transactional
	@Override
	public void updateType(Type type) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(type);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	@Transactional
	@Override
	public Type getTypeByName(String name) throws DAOException,
			ClassNotFoundException {

		Session session = null;
		Type findedType = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Object result = session.createCriteria(Type.class)
				.add(Restrictions.eq("name", name)).uniqueResult();

		findedType = (Type) result;

		if (session != null && session.isOpen()) {
			session.close();
		}

		return findedType;
	}

	@Transactional
	@Override
	public List<Attachment> getAttachSortByDate(int issueId)
			throws ClassNotFoundException, DAOException {
		Session session = null;
		session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery(SLC_FROM_ATTACH);

		query.setInteger(ID, issueId);

		List<Attachment> attach = query.list();
		for (Attachment attac : attach) {

		}
		if (session != null && session.isOpen()) {
			session.close();
		}

		return attach;
	}

	@Transactional
	@Override
	public void addNewUser(Employee employee) throws DAOException,
			ClassNotFoundException {
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();

		} catch (HibernateException e) {

			logger.error(HYB_ERR + e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

}
