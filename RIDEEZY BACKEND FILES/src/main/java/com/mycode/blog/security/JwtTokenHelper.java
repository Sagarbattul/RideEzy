package com.mycode.blog.security;

import org.springframework.stereotype.Component;

import com.mycode.blog.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.*;

@Component
public class JwtTokenHelper {

	public static final long JWT_TOKEN_VALIDITY = 5*60*60;
	
	private String secret = "jwtTokenKey";
	
	//retrieve username from jwt token
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	
	//retrieve expirattion date from jwt token
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	
	//for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	//check if the token has expired
	private Boolean isTokenExpired(String token)
	{
		final Date expiration = getExpirationDateFromToken(token);
		
		return expiration!=null && expiration.before(new Date());
	}
	
	//generate token for user
	public String generateToken(User userDetails)
	{
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getEmail());
	}
	
	//while creating the token
	//1. define claims of the token, like issuer, expiration, subject and the id
	//2. sign the jwt using the HS512 algorithm and secret key
	//3. according to jws compact serialization(http://tools.ietf.org/html/draft-ietf-jose-j)
	//4. compaction of the jwt to a url-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*100))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	//validate token
	public Boolean validateToken(String token, UserDetails userDetails)
	{
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	
	
}
