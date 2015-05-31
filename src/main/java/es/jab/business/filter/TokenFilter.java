package es.jab.business.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.io.IOException;
import java.security.Key;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import es.jab.persistence.dao.UserDao;
import es.jab.persistence.model.Token;
import es.jab.persistence.model.User;
import es.jab.utils.json.JsonTransformer;

public class TokenFilter implements Filter {
	
	@Autowired
	private TokenValidator tokenValidator;
	
	@Autowired
	private JsonTransformer jsonTransformer;
	
	@Autowired
	private UserDao userDao;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = ((HttpServletRequest) request).getHeader("Authorization");
        
        if (authorizationHeader == null) {
            throw new ServletException("Unauthorized: No Authorization header was found");
        }
        
        String subject = tokenValidator.validate(authorizationHeader);
        
        Token token = (Token) jsonTransformer.fromJson(subject, Token.class);
        
        User user = userDao.getUserByEmail(token.getEmail());
        
        if(user != null && user.getEmail().equals(token.getEmail())){
        	chain.doFilter(request, response);
        }
        else{    
        	throw new ServletException("Unauthorized: No Authorization header was found");
        }
        
	}

	@Override
	public void destroy() {		
	}

}
