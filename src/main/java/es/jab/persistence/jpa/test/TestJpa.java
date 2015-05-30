package es.jab.persistence.jpa.test;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public class TestJpa { 
	
	private EntityManager em;
	
	public TestJpa(){
		Map<String, String> properties = new HashMap<>();
		//properties.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
		em = Persistence.createEntityManagerFactory("NoteAndReminderApi", properties).createEntityManager();
	}
	
	public String testJpaPersistAndFind(Message messge){
		Message message = messge;
        
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
        
        Message messageBD = em.find(Message.class, 1);
        return messageBD.getMessage();
	}
	
	public static void main(String[] args) {
		
		TestJpa testJpa = new TestJpa();
		String value = testJpa.testJpaPersistAndFind(new Message("Hello World!"));
		System.out.println(value);

	}
}
