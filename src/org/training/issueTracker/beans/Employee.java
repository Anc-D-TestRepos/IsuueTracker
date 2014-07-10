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
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="Employee")
public class Employee {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="idEmail")
	private int id;
	
	@NotBlank(message = "please enter first name")
	@Column(name="firstName")
	private String firstName;
	
	@NotBlank(message = "please enter last name")
	@Column(name="lastName")
	private String lastName;
	
	@NotBlank(message = "please enter email")
	@Column(name="email")
	private String email;
	
	@Column(name="role")
	private String role;
	
	@NotBlank(message = "please enter password")
	@Column(name="pass")
	private String password;
	
	@OneToMany(mappedBy="assignee")
	
	private Set<Issue> issues = new HashSet<Issue>(0);
	
	public Employee() {
		super();
	
	}
	
	
	
	public Employee(int id, String firstName, String lastName, String email,
			String role, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
	}



	public Employee(String firstName, String lastName, String email, String role,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
	}
	
	public Employee( String email ) {
		super();
		this.email = email;
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



	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
