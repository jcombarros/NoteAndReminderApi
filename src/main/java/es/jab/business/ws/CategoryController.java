package es.jab.business.ws;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jab.persistence.dao.CategoryDao;
import es.jab.persistence.model.Category;
import es.jab.utils.json.JsonTransformer;

@Controller
public class CategoryController {
	@Autowired
	private JsonTransformer jsonTransformer;
	
	public void setJsonTransformer(JsonTransformer jsonTransformer){
		this.jsonTransformer = jsonTransformer;
	}
	
	@Autowired
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao){
		this.categoryDao = categoryDao;
	}
	
	@RequestMapping(value = "/Category/{idCategory}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idCategory") int idCategory) {
		try {
			Category category = categoryDao.read(idCategory);
			String jsonSalida = jsonTransformer.toJson(category);
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }
 
    @RequestMapping(value = "/Category", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
		try {
			Category category = (Category) jsonTransformer.fromJson(jsonEntrada, Category.class);
			categoryDao.create(category);
		    String jsonSalida = jsonTransformer.toJson(category);
		     
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		}     
		catch (Exception ex) {
		 	ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

    }

    @RequestMapping(value = "/Category", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		try {
		    List<Category> categories = categoryDao.findAll();
		    String jsonSalida = jsonTransformer.toJson(categories);
		     
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

    }
    
    @RequestMapping(value = "/Category/{idCategory}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idCategory") int idCategory) {
		try {
			Category category = (Category) jsonTransformer.fromJson(jsonEntrada, Category.class);
			categoryDao.update(category);
		    String jsonSalida = jsonTransformer.toJson(category);
		     
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }
    
    @RequestMapping(value = "/Category/{idCategory}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idCategory") int idCategory) {
		try {
			categoryDao.deleteById(idCategory);
		    httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}    
		catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }

}
