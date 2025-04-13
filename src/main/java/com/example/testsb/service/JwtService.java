package com.example.testsb.service;

import com.example.testsb.repository.MyUserRepository;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


import static io.jsonwebtoken.Jwts.*;

@Component
public class JwtService {
  @Autowired
    private MyUserRepository repository;

    // Replace this with a secure key in a real application, ideally fetched from environment variables
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";


    // Generate token with given user name
    public String generateToken(String userName, String userId, String userEmail) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,userName, userId, userEmail);
    }

    // Create a JWT token with specified claims and subject (user name)
    private String createToken(Map<String, Object> claims,String userName, String userId, String userEmail) {
        claims.put("userName", userName);
        claims.put("userId", userId);
        claims.put("userEmail", userEmail);
        return builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000000 * 60 * 30)) // Token valid for 30 minutes
                .signWith((SecretKey) getSignKey())
                .compact();
    }

    // Get the signing key for JWT token
    //return Key -> changed to Secret ke
    //if doesnt work try PublicKey
    //it is used in othe method here
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractId(String token) {return extractClaim(token, claims -> claims.get("userId",String.class));}
    // Extract the username from the token
    public String extractUsername(String token) {

        return extractClaim(token, claims -> claims.get("userName",String.class));
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //stackoverflow solution
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey((SecretKey) getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate the token against user details and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
