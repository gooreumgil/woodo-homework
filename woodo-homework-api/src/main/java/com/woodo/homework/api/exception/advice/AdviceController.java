package com.woodo.homework.api.exception.advice;

import com.woodo.homework.api.exception.AuthorizationFailureException;
import com.woodo.homework.api.exception.HttpException;
import com.woodo.homework.api.exception.NoPermissionException;
import com.woodo.homework.api.exception.dto.ErrorResponse;
import com.woodo.homework.api.exception.dto.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), e.getCode().name());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AuthorizationFailureException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationFailureException(AuthorizationFailureException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<ErrorResponse> handleNoPermissionException(NoPermissionException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            validationErrorResponse.getErrors().add(error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorResponse);
    }

}
