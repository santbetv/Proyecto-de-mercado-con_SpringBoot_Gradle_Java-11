package com.santbetv.developermercado.web.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	
	private static final String KEY ="my&/()contra@@%+";
	
	public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

	
	/**
	 * Metodo que me permite validar si es correcto.
	 * 
	 * @param token
	 * @param userDetails
	 * @return
	 */
    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }

    
    //extraer el usuario del subject
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    //validar si la fecha de expiracion esta corarrecta
    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
    
    //extrer los claims para validar valida la firma si esta ok
    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
