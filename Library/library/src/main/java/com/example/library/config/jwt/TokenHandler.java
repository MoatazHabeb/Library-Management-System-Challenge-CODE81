package com.example.library.config.jwt;

import com.example.library.model.usermodel.Roles;
import com.example.library.model.usermodel.Users;
import com.example.library.sitting.TokenConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class TokenHandler {


    private JwtBuilder jwtBuilder;
    public JwtParser jwtParser;

    private String secret;

    private Duration timeExpired;

    public TokenHandler(TokenConfig tokenConfig) {

        secret = tokenConfig.getSecret();
        timeExpired = tokenConfig.getTime();
        Key key =  Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        jwtBuilder = Jwts.builder().signWith(key);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();


    }


    public String createToken(Users users){


        for(Roles role : users.getRoles()){
            role.setUsers(null);

        }

        Date issuesDate = new Date();

        Date dateOfExpired = Date.from(issuesDate.toInstant().plus(timeExpired));

        return jwtBuilder.setSubject(users.getEmail())
                .setIssuedAt(issuesDate)
                .setExpiration(dateOfExpired)
                .claim("name", users.getName())
                .claim("roles", users.getRoles())
                .compact();


    }

    public boolean validateToken(String token) {

        if (jwtParser.isSigned(token)) {

            // check if the token is valid
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            Date issue = claims.getIssuedAt();
            Date expired = claims.getExpiration();


            return expired.after(new Date()) && issue.before(expired);
        }

        return  false;
    }

    public String getSubject(String token) {

        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        return claims.getSubject();
    }
}
