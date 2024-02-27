package com.woodo.homework.api.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProvider jwtProvider;
    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication;
        authentication = getAuthentication((HttpServletRequest) request);

        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);

    }

    private Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(AUTHORIZATION);
        if (token == null || !token.contains(TOKEN_PREFIX)) {
            return null;
        }

        Claims claims = jwtProvider.getClaims(token.substring("Bearer ".length()));
        if (claims == null) {
            return null;
        }

        Set<GrantedAuthority> roles = new HashSet<>();
        String role = String.valueOf(claims.get("role"));
        roles.add(new SimpleGrantedAuthority("ROLE_" + role));

        MemberContext memberTokenMapper = new MemberContext(claims);

        return new UsernamePasswordAuthenticationToken(memberTokenMapper, null, roles);

    }


}
