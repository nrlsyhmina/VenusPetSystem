package model;

import java.util.Date;

public class Forum {
	private int forumID;
	private String forumTitle;
	private String forumDescription;
	private Date forumDate;
	private int custID;
	private String staffID;

	
	
	public Forum() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Forum(int forumID, String forumTitle, String forumDescription, Date forumDate, int custID, String staffID) {
		super();
		this.forumID = forumID;
		this.forumTitle = forumTitle;
		this.forumDescription = forumDescription;
		this.forumDate = forumDate;
		this.custID = custID;
		this.staffID = staffID;
	}


	public int getForumID() {
		return forumID;
	}
	public void setForumID(int forumID) {
		this.forumID = forumID;
	}
	public String getForumTitle() {
		return forumTitle;
	}
	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	
	public Date getForumDate() {
		return forumDate;
	}
	public void setForumDate(Date forumDate) {
		this.forumDate = forumDate;
	}
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}


	public String getForumDescription() {
		return forumDescription;
	}


	public void setForumDescription(String forumDescription) {
		this.forumDescription = forumDescription;
	}


	public String getStaffID() {
		return staffID;
	}


	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	
	
	
}
