package es.jab.business.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

public class TokenValidator {
	
	private Key key;
	
	public TokenValidator() {
		super();
		key = MacProvider.generateKey();
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}
	
	public String sign(String subject){
		return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS512, this.getKey()).compact();
	}
	
	public String validate(String signature){
		return Jwts.parser().setSigningKey(this.getKey()).parseClaimsJws(signature).getBody().getSubject();
	}
	
	

}
