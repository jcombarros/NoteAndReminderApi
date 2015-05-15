package es.jab.persistence.model;

import java.util.Date;

public abstract class Annotation {
	
	protected int id;
	
	protected String title;
	
	protected User user;
	
	protected Date creationDate;

	public Annotation(int id, String title, User user, Date creationDate) {
		super();
		this.id = id;
		this.title = title;
		this.user = user;
		this.creationDate = creationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
