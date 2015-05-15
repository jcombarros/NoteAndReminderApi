package es.jab.persistence.model;

import java.util.Date;

public class Reminder extends Annotation {


	private Date completionDate;
	
	private boolean isCompleted;
	
	public Reminder(String title, User user){
		this(0, title,user,new Date(), null, false);
	}
	
	public Reminder(int id, String title, User user, Date creationDate, Date completionDate, boolean isCompleted) {
		super(id, title, user, creationDate);
		this.setCompletionDate(completionDate);
		this.setCompleted(isCompleted);
	}


	public Date getCompletionDate() {
		return completionDate;
	}


	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}


	public boolean isCompleted() {
		return isCompleted;
	}


	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
	@Override
	public String toString(){
		return this.id + ": " + this.title + " BY " + this.user.getName() + " " + this.user.getSurname();
	}

}
