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
@Table(name="priority")

public class Priority {

	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="priorityId")
	private int id;	
	
	@Column(name="name")
	private String name;
	
	@Column(name="color")
	private String color;
	
	

	@OneToMany(mappedBy="priority")
	private Set<Issue> issues = new HashSet<Issue>(0);
	
	
	public Priority(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		color = "black";
	}
	
	public Priority(String name) {
		super();
		this.name = name;
		color = "black";
	}
	
	public Priority() {
		super();
		
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


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	

}
