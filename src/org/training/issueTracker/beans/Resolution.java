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
@Table(name="resolution")


public class Resolution {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="resolutionId")
	private int id;	
	
	@Column(name="name")
	private String name;
	
	
	@OneToMany(mappedBy="resolution")
	private Set<Issue> issues = new HashSet<Issue>(0);
	
	
	public Resolution(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Resolution(String name) {
		super();
		
		this.name = name;
	}
	
	public Resolution() {
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

}
