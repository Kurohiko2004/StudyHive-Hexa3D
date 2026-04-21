package com.sep.userService.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    // 1. Secret Key & Expiration Configuration
    // These values MUST be defined in your application.yml/properties file
    @Value("${application.security.jwt.secret-key}")
    private String secretKey; // The base64-encoded string used to sign tokens

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration; // Duration in milliseconds before the token becomes invalid

    // 2. Generate Token (Main Method)
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Helper to build the token with extra claims
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        /*
         * .setClaims: Sets custom data (roles, id, etc.)
         * .setSubject: Sets the unique identifier (usually email/username)
         * .signWith: Uses the secret key to ensure the token cannot be tampered with
         * .compact: Finalizes the building process and returns the string
         */
        return Jwts.builder()
                .setClaims(extraClaims) // Add custom info (e.g., roles, userId)
                .setSubject(userDetails.getUsername()) // Usually the email
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) // Sign with secret key
                .compact();
    }

    // 4. Validate Token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        // Token is valid if:
        // - Username matches the user details
        // - Token is not expired
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // 3. Extract Username (Subject) - Used to identify who the token belongs to
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Checks if the current date is past the token's expiration date
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 5. Extract specific claim using a resolver function
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 6. Parse the token to get all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 7. Decode the Base64 secret key into a Key object for HMAC-SHA algorithms
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
