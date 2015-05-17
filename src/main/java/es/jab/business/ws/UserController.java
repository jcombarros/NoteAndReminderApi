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

import es.jab.persistence.dao.UserDao;
import es.jab.persistence.model.User;
import es.jab.utils.json.JsonTransformer;

@Controller
public class UserController {
	
	@Autowired
	private JsonTransformer jsonTransformer;
	
	public void setJsonTransformer(JsonTransformer jsonTransformer){
		this.jsonTransformer = jsonTransformer;
	}
	
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
	@RequestMapping(value = "/User/{idUser}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idUser") int idUser) {
		try {
			User user = userDao.read(idUser);
			String jsonSalida = jsonTransformer.toJson(user);
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }
 
    @RequestMapping(value = "/User", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
		try {
			User user = (User) jsonTransformer.fromJson(jsonEntrada, User.class);
		 	userDao.create(user);
		    String jsonSalida = jsonTransformer.toJson(user);
		     
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		}     
		catch (Exception ex) {
		 	ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

    }

    @RequestMapping(value = "/User", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		try {
		    List<User> users = userDao.findAll();
		    String jsonSalida = jsonTransformer.toJson(users);
		     
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

    }
    
    @RequestMapping(value = "/User/{idUser}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idUser") int idUser) {
		try {
			User user = (User) jsonTransformer.fromJson(jsonEntrada, User.class);
		    userDao.update(user);
		    String jsonSalida = jsonTransformer.toJson(user);
		     
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }
    
    @RequestMapping(value = "/User/{idUser}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idUser") int idUser) {
		try {
		    userDao.deleteById(idUser);
		    httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}    
		catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }

}
