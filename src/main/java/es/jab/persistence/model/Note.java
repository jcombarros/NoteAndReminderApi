package es.jab.persistence.model;

import java.util.Date;

public class Note extends Annotation {
	
	private String description;
	
	public Note(String title, User user, String descripcion){
		this(0,title, user, new Date(), descripcion);
	}

	public Note(int id, String title, User user, Date creationDate, String description) {
		super(id, title, user, creationDate);
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
