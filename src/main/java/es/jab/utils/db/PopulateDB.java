package es.jab.utils.db;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import es.jab.persistence.model.Category;
import es.jab.persistence.model.Note;
import es.jab.persistence.model.Reminder;
import es.jab.persistence.model.User;

public class PopulateDB {

	private EntityManager em;
		
		public PopulateDB(){
			Map<String, String> properties = new HashMap<>();
			properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
			em = Persistence.createEntityManagerFactory("NoteAndReminderApi", properties).createEntityManager();
		}
		
		public void testJpaPersistAndFind(){
			try {
				User user = new User("Javier", "Combarros", "j.combarros@gmail.com", "myPass", "myPass");
				Note note = new Note("My First Note", user, "These are my first lines...");
				Reminder reminder = new Reminder("My first Reminder", user);
				Category category = new Category("My First Stuff");
				
				category.addNote(note);
				category.addReminder(reminder);
				
				user.addNote(note);
				user.addReminder(reminder);
				
				em.getTransaction().begin();
				
				em.persist(user);
				em.persist(category);
				em.persist(note);
				em.persist(reminder);
				
				em.getTransaction().commit();
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			
			System.out.println("START -- populating DB");
			
			PopulateDB populateDB = new PopulateDB();
			populateDB.testJpaPersistAndFind();
			
			System.out.println("FINISH -- populating DB");
	
		}
	}