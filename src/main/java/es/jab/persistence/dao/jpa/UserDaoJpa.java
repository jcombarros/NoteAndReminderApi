package es.jab.persistence.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import es.jab.persistence.dao.UserDao;
import es.jab.persistence.model.User;



public class UserDaoJpa extends GenericDaoJpa<User,Integer>implements UserDao {

    public UserDaoJpa() {
        super(User.class);
    }

	@Override
	public User getUserByEmail(String email) {
		EntityManager entityManager = GenericDaoJpa.getEntityManagerFactory().createEntityManager();
		User user;
		try{
	        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	        CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root =  query.from(User.class);
			query.select(root);
			query.where(builder.equal(root.get("email"), email));
			TypedQuery<User> typedQuery = entityManager.createQuery(query);
			user = typedQuery.getSingleResult();
		}
		catch(Exception e){
			user = null;
		}finally{
			entityManager.close();
		}
		return user;
	}
    
}