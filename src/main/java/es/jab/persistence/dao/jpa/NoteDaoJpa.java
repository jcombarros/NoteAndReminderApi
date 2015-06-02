package es.jab.persistence.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import es.jab.persistence.dao.NoteDao;
import es.jab.persistence.dao.UserDao;
import es.jab.persistence.model.Note;
import es.jab.persistence.model.User;

public class NoteDaoJpa extends GenericDaoJpa<Note,Integer>implements NoteDao {
	
	@Autowired
	private UserDao userDao;

    public NoteDaoJpa() {
        super(Note.class);
    }
    
    @Override
	public List<Note> getByUser(int userId) {
		EntityManager entityManager = GenericDaoJpa.getEntityManagerFactory().createEntityManager();
		
		User user = userDao.read(userId);
		
		List<Note> notes;
		try{
	        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<Note> query = builder.createQuery(Note.class);
			Root<Note> root =  query.from(Note.class);
			query.select(root);
			query.where(builder.equal(root.get("user").as(User.class), user));
			TypedQuery<Note> typedQuery = entityManager.createQuery(query);
			notes = typedQuery.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
			notes = null;
		}finally{
			entityManager.close();
		}
		return notes;
	}
}
