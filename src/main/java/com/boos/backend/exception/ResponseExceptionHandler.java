package com.boos.backend.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    // Maneja cualquier error no controlado y responde con estado 500.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorTemplate> handleDefaultException(Exception ex, WebRequest request) {
        CustomErrorTemplate error = new CustomErrorTemplate(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // Maneja cuando no se encuentra un registro en la base de datos.
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorTemplate> handleModelNotFoundException(ModelNotFoundException ex, WebRequest request) {
        CustomErrorTemplate error = new CustomErrorTemplate(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Maneja errores de cálculo, por ejemplo división entre cero.
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<CustomErrorTemplate> handleArithmeticException(ArithmeticException ex, WebRequest request) {
        CustomErrorTemplate error = new CustomErrorTemplate(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }

    // Maneja errores de validación enviados por los DTO o formularios.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        CustomErrorTemplate error = new CustomErrorTemplate(
                LocalDateTime.now(),
                msg,
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}