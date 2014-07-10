package org.training.issueTracker.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Build")


public class Build {
	
	

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idBuild")
	private int id;	
	
	@Column(name="build")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "projectID")
	Project project;

	
	
	public Build() {
		super();
		
	}

	public Build(int id, String name, Project project) {
		super();
		this.id = id;
		this.name = name;
		this.project = project;
	}

	public Build(String name, Project project) {
		super();
		this.name = name;
		this.project = project;
	}
	
	public Build(String name) {
		super();
		this.name = name;
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	
	
}
