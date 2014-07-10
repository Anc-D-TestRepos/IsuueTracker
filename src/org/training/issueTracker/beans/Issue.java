package org.training.issueTracker.beans;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="Issue")

public class Issue   {


	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idIssue")
	private int id ;
	
	@Column(name="createDate")
	private Date createDate;
	
	@Column(name="createBy")
	private String createBy;
	
	@Column(name="modifiedDate")
	private Date modifiedDate;
	
	@Column(name="modifiedBy")
	private String modifiedBy;
	
	@Column(name="summary")
	private String summary;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "statusId")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "resolutionId")
	private Resolution resolution;
	
	@ManyToOne
	@JoinColumn(name = "typeId")
	private Type type;
	
	@ManyToOne
	@JoinColumn(name = "priorityId")
	private Priority priority;
	
	@ManyToOne
	@JoinColumn(name = "projectId")
	private Project projectName;
	
	@Column(name="buildFound")
	private String buildFound;
	
	@ManyToOne
	@JoinColumn(name = "assigneeId")
	private Employee assignee;
	
	@OneToMany(mappedBy="issue")
	private Set<Comment> comments = new HashSet<Comment>(0);
	
	@OneToMany(mappedBy="issue")
	private Set<Attachment> attachments = new HashSet<Attachment>(0);

	/**
	 * 
	 */
	public Issue() {
		super();
		
	}
	
	/**
	 * 
	 * @param id
	 * @param priority
	 * @param assignee
	 * @param type
	 * @param status
	 * @param summary
	 *  @param color
	 */
	public Issue(int id, Priority priority, Employee assignee, Type type,
			Status status, String summary) {
		super();
		this.id = id;
		this.priority = priority;
		this.assignee = assignee;
		this.type = type;
		this.status = status;
		this.summary = summary;
		
	}
	
	
	public Issue(int id, Date createDate, String createBy,Date modifiedDate, String modifiedBy,
			String summary, String description, Status status,
			Resolution resolution, Type type, Priority priority,
			Project projectName, String buildFound, Employee assignee	) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.createBy = createBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.resolution = resolution;
		this.type = type;
		this.priority = priority;
		this.projectName = projectName;
		this.buildFound = buildFound;
		this.assignee = assignee;
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	

	
	/**
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}
	

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * @return the assignee
	 */
	public Employee getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(Employee assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the resolution
	 */
	public Resolution getResolution() {
		return resolution;
	}
	
	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return projectName;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the buildFount
	 */
	public String getBuildFound() {
		return buildFound;
	}

	/**
	 * @param buildFount the buildFount to set
	 */
	public void setBuildFound(String buildFound) {
		this.buildFound = buildFound;
	}
	

	/**
	 * @return the projectName
	 */
	public Project getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(Project projectName) {
		this.projectName = projectName;
	}

}


