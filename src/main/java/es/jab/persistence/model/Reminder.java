package es.jab.persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="REMINDER")
public class Reminder extends Annotation {

	@Temporal(TemporalType.DATE)
	private Date completionDate;
	
	private boolean isCompleted;
	
	public Reminder(){
		this(0, "", null, new Date(), null, false);
	}
	
	public Reminder(String title, User user){
		this(0, title,user,new Date(), null, false);
	}
	
	private Reminder(int id, String title, User user, Date creationDate, Date completionDate, boolean isCompleted) {
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
	
	@Override
	public void setUser(User user){
		if(user != null){
			user.addReminder(this);
		}
		
	}
	
	@Override
	public void setCategory(Category category){
		if(category != null){
			category.addReminder(this);
		}
		
	}

}
