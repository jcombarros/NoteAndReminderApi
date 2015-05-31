package es.jab.business.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jab.business.filter.TokenValidator;
import es.jab.persistence.dao.UserDao;
import es.jab.persistence.model.Token;
import es.jab.persistence.model.User;
import es.jab.utils.json.JsonTransformer;

@Controller
public class AuthenticationController {
	
	@Autowired
	private TokenValidator tokenValidator;
	
	@Autowired
	private JsonTransformer jsonTransformer;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void authenticate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
		try {
			Token token = (Token) jsonTransformer.fromJson(jsonEntrada, Token.class);
			
			boolean authorized = false;
			if(token != null && token.getEmail() != null){
				User user = userDao.getUserByEmail(token.getEmail());
				if(user != null && user.getPassword().equals(token.getPassword())){
					
					String signature = tokenValidator.sign(jsonEntrada);
					
					user.setToken(signature);
					userDao.update(user);
					
					token.authorize(signature);
					authorized = true;
				}
				else{
					token.deny("Incorrect email/password");
				}
			}
			
			String jsonSalida = jsonTransformer.toJson(token);
			
			if(authorized){	
				httpServletResponse.setStatus(HttpServletResponse.SC_OK);
				httpServletResponse.setContentType("application/json; charset=UTF-8");
			    httpServletResponse.getWriter().println(jsonSalida);
			}
			else{
				httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

    }

}
