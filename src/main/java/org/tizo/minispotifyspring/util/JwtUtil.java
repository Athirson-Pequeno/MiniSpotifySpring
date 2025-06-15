package org.tizo.minispotifyspring.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key key = Keys.hmacShaKeyFor("chave-super-secreta-que-deve-ter-pelo-menos-32-bytes".getBytes());
    private static final long EXPIRATION_TIME = 6000000;


    public static String gerarToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static String getEmailDoToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static boolean tokenValido(String token) {
        try {
            getEmailDoToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
