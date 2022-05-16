package com.petkit.service.implement;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.Calendar;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import com.petkit.service.SecurityService;

@Component
public class SecurityServiceImpl implements SecurityService {
	private static final String SECRET_KEY= "aasjjkjaskjdl1k2naskjkdakj34c8sa";
	
	private int tokenExpirationMsec = 1000 * 60 *60 * 24; // 하루
	
	@Override
	public String createToken(String subject) {
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, tokenExpirationMsec);
		Date expireDate = calendar.getTime();
		
		byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        return Jwts.builder()
        		.setSubject(subject)
        		.setIssuedAt(now)
        		.setExpiration(expireDate)
        		.signWith(signatureAlgorithm, signingKey)
        		.compact();
	}
	
	@Override
	public boolean validateToken(String token) throws JwtException{
		try {
			Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
				.parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getSubject(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}


}
