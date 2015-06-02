package es.jab.persistence.dao;

import java.util.List;

import es.jab.persistence.model.Note;

public interface NoteDao  extends GenericDao<Note, Integer> {
	
	public abstract List<Note> getByUser(int userId);

}
