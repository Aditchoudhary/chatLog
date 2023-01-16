package com.ofBusiness.chatLogs.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "chatlog")
@Entity(name = "chatlog")
public class ChatLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chatlogid")
	private int id;
	
	@Column(name = "user")
    private String user;
	
	@Column(name = "message")
    private String message;
	
	@Column(name = "issent")
    private boolean issent;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "create_Date")
	private Timestamp createdDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isIssent() {
		return issent;
	}

	public void setIssent(boolean issent) {
		this.issent = issent;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getCreated_date() {
		return createdDate;
	}

	public void setCreated_date(Timestamp created_date) {
		this.createdDate = created_date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
    

}
