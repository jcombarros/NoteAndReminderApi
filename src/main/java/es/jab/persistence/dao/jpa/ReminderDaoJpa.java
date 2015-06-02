package es.jab.persistence.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import es.jab.persistence.dao.ReminderDao;
import es.jab.persistence.dao.UserDao;
import es.jab.persistence.model.Note;
import es.jab.persistence.model.Reminder;
import es.jab.persistence.model.User;

public class ReminderDaoJpa extends GenericDaoJpa<Reminder,Integer>implements ReminderDao {
	
	@Autowired
	private UserDao userDao;

    public ReminderDaoJpa() {
        super(Reminder.class);
    }
    
    @Override
	public List<Reminder> getByUser(int userId) {
		EntityManager entityManager = GenericDaoJpa.getEntityManagerFactory().createEntityManager();
		
		User user = userDao.read(userId);
		
		List<Reminder> reminders;
		try{
	        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Reminder> query = builder.createQuery(Reminder.class);
			Root<Reminder> root =  query.from(Reminder.class);
			query.select(root);
			query.where(builder.equal(root.get("user").as(User.class), user));
			TypedQuery<Reminder> typedQuery = entityManager.createQuery(query);
			reminders = typedQuery.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			reminders = null;
		}finally{
			entityManager.close();
		}
		return reminders;
	}
}
