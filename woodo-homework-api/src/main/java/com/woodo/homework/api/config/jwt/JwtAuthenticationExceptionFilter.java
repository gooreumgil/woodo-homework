package com.woodo.homework.api.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woodo.homework.api.constants.ExceptionConstants;
import com.woodo.homework.api.exception.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.woodo.homework.api.constants.ExceptionConstants.*;

public class JwtAuthenticationExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            String errorMessage = getErrorMessage(e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ErrorResponse errorResponse = new ErrorResponse(errorMessage);
            objectMapper.writeValue(response.getWriter(), errorResponse);
        }
    }

    private String getErrorMessage(Exception e) {
        if (e instanceof MalformedJwtException) {
            return MALFORMED_JWT_EXCEPTION_MESSAGE;
        } else if (e instanceof ExpiredJwtException) {
            return EXPIRED_JWT_EXCEPTION_MESSAGE;
        } else {
            return DEFAULT_JWT_EXCEPTION_MESSAGE;
        }
    }

}
