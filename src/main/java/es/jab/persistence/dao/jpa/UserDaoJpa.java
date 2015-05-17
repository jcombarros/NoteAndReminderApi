package es.jab.persistence.dao.jpa;

import es.jab.persistence.dao.UserDao;
import es.jab.persistence.model.User;



public class UserDaoJpa extends GenericDaoJpa<User,Integer>implements UserDao {

    public UserDaoJpa() {
        super(User.class);
    }
    
}