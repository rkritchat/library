package com.library.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.library.constant.SecurityConstant.HEADER_STRING;
import static com.library.constant.SecurityConstant.KEY;
import static com.library.constant.SecurityConstant.PREFIX_STRING;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(PREFIX_STRING)) {
            System.out.println("Header is null");
        }else{
            UsernamePasswordAuthenticationToken tokenFromHeader = getTokenFromHeader(header);
            SecurityContextHolder.getContext().setAuthentication(tokenFromHeader);
        }

        chain.doFilter(request,response);
    }

    public UsernamePasswordAuthenticationToken getTokenFromHeader(String header) {
        Claims body = Jwts.parser().setSigningKey(KEY)
                .parseClaimsJws(header.replace(PREFIX_STRING, ""))
                .getBody();

        String username = body.getSubject();
        String role = body.getAudience();
        System.out.println("Username "+ username +" role : " + role);
        return username == null ? null : new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.createAuthorityList(role));

    }
}
