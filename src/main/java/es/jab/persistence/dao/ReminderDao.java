package es.jab.persistence.dao;

import java.util.List;

import es.jab.persistence.model.Reminder;

public interface ReminderDao  extends GenericDao<Reminder, Integer> {

	public abstract List<Reminder> getByUser(int userId);
	
}
