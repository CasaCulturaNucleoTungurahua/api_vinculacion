package com.casaculturatungurahua.api.security.jwt;

import com.casaculturatungurahua.api.security.model.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.security.Key;

@Component
public class JWTProvider {

    private final Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .signWith(SECRET)
                .compact();
    }

    public String getUsernameFromJWT(String jwt){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();

    }
    public boolean validateJWT(String jwt){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(jwt);
            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
