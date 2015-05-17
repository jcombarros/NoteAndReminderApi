package es.jab.persistence.dao.jpa;

import es.jab.persistence.dao.CategoryDao;
import es.jab.persistence.model.Category;

public class CategoryDaoJpa extends GenericDaoJpa<Category,Integer>implements CategoryDao {

    public CategoryDaoJpa() {
        super(Category.class);
    }
    
}