package es.jab.persistence.dao.jpa;

import es.jab.persistence.dao.NoteDao;
import es.jab.persistence.model.Note;

public class NoteDaoJpa extends GenericDaoJpa<Note,Integer>implements NoteDao {

    public NoteDaoJpa() {
        super(Note.class);
    }
}
