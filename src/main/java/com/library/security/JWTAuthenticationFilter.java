package com.library.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.model.StudentIdentify;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import static com.library.constant.SecurityConstant.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            StudentIdentify studentIdentify = new ObjectMapper().readValue(request.getInputStream(), StudentIdentify.class);
            System.out.println(studentIdentify.toString());
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(studentIdentify.getUsername(),studentIdentify.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ZonedDateTime expirationTime = ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME, ChronoUnit.MINUTES);
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, KEY)
                .setExpiration(Date.from(expirationTime.toInstant()))
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setAudience(authResult.getAuthorities().toArray()[0].toString())
                .compact();

        response.setHeader(HEADER_STRING,PREFIX_STRING+token);
    }
}
