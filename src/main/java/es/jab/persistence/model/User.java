package es.jab.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private int id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private List<Note> notes;
	
	private List<Reminder> reminders;
	
	public User(String name, String surname, String email, String password, String passwordAgain) throws Exception{
		this(0, name, surname, email, password);
		if(!password.equals(passwordAgain)){
			throw new Exception();
		}
	}
	
	public User(int id, String name, String surname, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.setNotes(new ArrayList<Note>());
		this.setReminders(new ArrayList<Reminder>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	@Override
	public String toString(){
		return this.id + ": " + this.name + " " + this.surname;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}
	
	public void addNote(Note note){
		this.notes.add(note);
	}
	
	public void removeNote(Note note){
		this.notes.remove(note);
	}
	
	public void addReminder(Reminder reminder){
		this.reminders.add(reminder);
	}
	
	public void removeReminder(Note note){
		this.reminders.remove(note);
	}

}
