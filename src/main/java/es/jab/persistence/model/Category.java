package es.jab.persistence.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private int id;
	
	private String name;
	
	private List<Annotation> annotations;
	
	public Category(String name){
		this(0, name);
		
	}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.annotations = new ArrayList<Annotation>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}
	
	public void addAnnotation(Annotation annotation){
		this.annotations.add(annotation);
	}
	
	public void removeAnnotation(Annotation annotation){
		this.annotations.remove(annotation);
	}

}
