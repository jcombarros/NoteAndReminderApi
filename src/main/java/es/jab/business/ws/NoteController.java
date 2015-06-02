package es.jab.business.ws;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jab.business.filter.TokenValidator;
import es.jab.persistence.dao.NoteDao;
import es.jab.persistence.dao.UserDao;
import es.jab.persistence.model.Note;
import es.jab.persistence.model.Token;
import es.jab.persistence.model.User;
import es.jab.utils.json.JsonTransformer;

@Controller
public class NoteController {
	
	@Autowired
	private JsonTransformer jsonTransformer;
	
	@Autowired
	private NoteDao noteDao;
	
	@Autowired
	private TokenValidator tokenValidator;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/Note/{idNote}", method = RequestMethod.GET, produces = "application/json")
    public void read(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idNote") int idNote) {
		try {
			Note note = noteDao.read(idNote);
			if(note == null){
				httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}else{
				String jsonSalida = jsonTransformer.toJson(note);
				httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			    httpServletResponse.setContentType("application/json; charset=UTF-8");
			    httpServletResponse.getWriter().println(jsonSalida);
			}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		     
		}
    }
 
    @RequestMapping(value = "/Note", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void insert(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
		try {
			Note note = (Note) jsonTransformer.fromJson(jsonEntrada, Note.class);
			note.setUser(getUserFromRequest(httpServletRequest));
			noteDao.create(note);
			String jsonSalida = jsonTransformer.toJson(note);
			      
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			httpServletResponse.setContentType("application/json; charset=UTF-8");
			httpServletResponse.getWriter().println(jsonSalida);
		}     
		catch (Exception ex) {
			ex.printStackTrace();
			httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);   
		}

}

    @RequestMapping(value = "/Note/userId={userId}", method = RequestMethod.GET, produces = "application/json")
    public void findByUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("userId") int userId) {
    	try {
		    List<Note> notes = noteDao.getByUser(userId);
		    String jsonSalida = jsonTransformer.toJson(notes);
		     
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }
    
    @RequestMapping(value = "/Note/{idNote}", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("idNote") int idNote) {
    	try {
    		Note note = (Note) jsonTransformer.fromJson(jsonEntrada, Note.class);
    		noteDao.update(note);
		    String jsonSalida = jsonTransformer.toJson(note);
		     
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		    httpServletResponse.setContentType("application/json; charset=UTF-8");
		    httpServletResponse.getWriter().println(jsonSalida);
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }
    
    @RequestMapping(value = "/Note/{idNote}", method = RequestMethod.DELETE, produces = "application/json")
    public void delete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("idNote") int idNote) {
    	try {
		    noteDao.deleteById(idNote);
		    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		}    
		catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    }
    
    private User getUserFromRequest(HttpServletRequest request){
    	String authorizationHeader =  request.getHeader("Authorization");
        String subject = tokenValidator.validate(authorizationHeader);
        Token token = (Token) jsonTransformer.fromJson(subject, Token.class);
        return userDao.getUserByEmail(token.getEmail());
    }
}
