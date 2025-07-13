package com.example.demo.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


import java.security.Key;
import java.util.*;

public class JWTUtil {
    private final Key key;
    public long EXPIRATION_TIME = 1000 * 60 * 60;


    public JWTUtil(String secret)
    {
            this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Map<String, Object> claims)
    {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateToken(String token)
    {
        try
        {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJwt(token)
                    .getBody();
        }
        catch (JwtException jex)
        {
            //logg this
            return null;
        }
    }

}
