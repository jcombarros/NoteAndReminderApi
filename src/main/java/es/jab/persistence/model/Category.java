package es.jab.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="CATEGORY")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "category")
	private List<Note> notes;
	
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "category")
	private List<Reminder> reminders;
	
	public Category(){
		this(0, "");
	}
	
	public Category(String name){
		this(0, name);
		
	}

	private Category(int id, String name) {
		super();
		this.setId(id);
		this.setName(name);
		this.setNotes(new ArrayList<Note>());
		this.setReminders(new ArrayList<Reminder>());
	}
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Note> getNotes() {
		return notes;
	}

	private void setNotes(List<Note> reminders) {
		this.notes = reminders;
	}
	
	public List<Reminder> getReminders() {
		return reminders;
	}

	private void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}
	
	public void addNote(Note note) throws NullPointerException{
		if(note == null){
			throw new NullPointerException();
		}
		this.notes.add(note);
		note.category = this;
	}
	
	public void removeNote(Note note) throws NullPointerException{
		if(note == null){
			throw new NullPointerException();
		}
		note.category = null;
		this.notes.remove(note);
	}
	
	public void addReminder(Reminder reminder) throws NullPointerException{
		if(reminder == null){
			throw new NullPointerException();
		}
		this.reminders.add(reminder);
		reminder.category = this;
	}
	
	public void removeReminder(Reminder reminder) throws NullPointerException{
		if(reminder == null){
			throw new NullPointerException();
		}
		reminder.category = null;
		this.reminders.remove(reminder);
	}

}
