package es.jab.persistence.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class Annotation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	protected String title;
	
	@ManyToOne()
	@JoinColumn
	protected User user;
	
	@ManyToOne()
	@JoinColumn
	protected Category category;
	
	@Temporal(TemporalType.DATE)
	protected Date creationDate;
	
	protected Annotation() {
		this(0, "", null, null);
	}

	protected Annotation(int id, String title, User user, Date creationDate) {
		super();
		this.setId(id);
		this.setTitle(title);
		this.setUser(user);
		this.setCreationDate(creationDate);
	}

	public int getId() {
		return id;
	}

	protected void setId(int id) {
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

	public abstract void setUser(User user);

	public Category getCategory() {
		return category;
	}

	public abstract void setCategory(Category category);

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
