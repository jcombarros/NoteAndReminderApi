package es.jab.persistence.dao;

import es.jab.persistence.model.User;

public interface UserDao extends GenericDao<User, Integer> {
	
	public User getUserByEmail(String email);

}
