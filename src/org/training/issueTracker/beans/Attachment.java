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
@Table(name="Attachment")
public class Attachment {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="attachmentId")
	private int id;	
	
	@Column(name="attachment")
	private String fileName;
	
	@Column(name="addedBy")
	private String addedBy;
	
	@Column(name="addDate")
	private Date addDate;
	
	@ManyToOne
	@JoinColumn(name = "idAttachment")
	private Issue issue;

	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachment(int id, String fileName, String addedBy, Date addDate) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.addedBy = addedBy;
		this.addDate = addDate;
	}
	
	

	public Attachment(int id, String fileName, String addedBy) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.addedBy = addedBy;
		this.addDate = new Date(new java.util.Date().getTime());
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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param comment the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
