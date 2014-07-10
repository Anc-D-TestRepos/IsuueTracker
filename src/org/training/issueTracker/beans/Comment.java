package org.training.issueTracker.beans;
import java.sql.Date;

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
@Table(name="comments")
public class Comment {

	

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="commentId")
	private int id;	
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="addedBy")
	private String addedBy;
	
	@Column(name="addDate")
	private Date addDate;
	
	
	@ManyToOne
	@JoinColumn(name = "idComment")
	private Issue issue;
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Comment(int id, String comment, String addedBy, Date addDate) {
		super();
		this.id = id;
		this.comment = comment;
		this.addedBy = addedBy;
		this.addDate = addDate;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * @return the addedBy
	 */
	public String getAddedBy() {
		return addedBy;
	}
	
	/**
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	
	/**
	 * @return the addDate
	 */
	public Date getAddDate() {
		return addDate;
	}
	
	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	/**
	 * @return the issue
	 */
	public Issue getIssue() {
		return issue;
	}

	/**
	 * @param issue the issue to set
	 */
	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	


}
