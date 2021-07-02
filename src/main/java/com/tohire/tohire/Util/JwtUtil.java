package com.tohire.tohire.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import com.google.gson.Gson;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.gson.io.GsonSerializer;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
    
    private SecretKey SECRECT_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private String createToken(Map<String,Object> claims,String subject){
        Gson gson = new Gson();
        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuer("tohire")
                   .setSubject(subject)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                   .signWith(this.SECRECT_KEY)
                   .serializeToJsonWith(new GsonSerializer(gson)).compact();
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    public String extractUsername(String jwt){
        return extractClaim(jwt,Claims::getSubject);
    }

    public <T> T extractClaim(String jwt,java.util.function.Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String jwt){
        return Jwts.parserBuilder()
                   .setSigningKey(SECRECT_KEY)
                   .build()
                   .parseClaimsJws(jwt)
                   .getBody();
    }

    public boolean isTokenExpired(String jwt){
        return extractExpiration(jwt).before(new Date());
    }

    public Date extractExpiration(String jwt){
        return extractClaim(jwt,Claims::getExpiration);
    }

    public Boolean validateJwt(String jwt,UserDetails userDetails){
        
        final String username = extractUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));

        // try {
        //     Jwts.parser()
        //     .setSigningKey(SECRECT_KEY)
        //     .parseClaimsJws(jwt);
        //     return "valid jwt";
        // } catch (SignatureException e) {
		// 	return "Invalid JWT signature";
		// } catch (MalformedJwtException e) {
		// 	return "Invalid JWT token";
		// } catch (ExpiredJwtException e) {
		// 	return "JWT token is expired";
		// } catch (UnsupportedJwtException e) {
		// 	return "JWT token is unsupported";
		// } catch (IllegalArgumentException e) {
		// 	return "JWT claims string is empty";
		// }
    }

}
