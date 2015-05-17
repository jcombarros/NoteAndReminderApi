package es.jab.persistence.dao.jpa;

import es.jab.persistence.dao.ReminderDao;
import es.jab.persistence.model.Reminder;

public class ReminderDaoJpa extends GenericDaoJpa<Reminder,Integer>implements ReminderDao {

    public ReminderDaoJpa() {
        super(Reminder.class);
    }
}
