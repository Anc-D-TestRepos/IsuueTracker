package org.training.issueTracker.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="project")

public class Project {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idProject")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy="project")
	private Set<Build> builds = new HashSet<Build>(0);
	 
	@OneToMany(mappedBy="projectName")
	private Set<Issue> issues = new HashSet<Issue>(0);
	 	



	@Column(name="manager")
	private String manager;
	



	public Project() {
		super();
		
	}


	
	public Project(String name, String description, String manager) {
		super();
		this.name = name;
		this.description = description;
		this.manager = manager;
	}
	
	public Project(int id, String name, String description, String manager) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.manager = manager;
	}
	
	public Project(String name, String description, Set<Build> builds, String manager) {
	super();
	this.name = name;
	this.description = description;
	this.builds = builds;
	this.manager = manager;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	 public Set<Build> getBuilds() {
         return builds;
	 }
	 
	 public void setBuilds(Set<Build> builds) {
			this.builds = builds;
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
	 * @return the manager
	 */
	public String getManager() {
		return manager;
	}


	/**
	 * @param manager the manager to set
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}


	/**
	 * @return the issues
	 */
	public Set<Issue> getIssues() {
		return issues;
	}


	/**
	 * @param issues the issues to set
	 */
	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}
	
}
