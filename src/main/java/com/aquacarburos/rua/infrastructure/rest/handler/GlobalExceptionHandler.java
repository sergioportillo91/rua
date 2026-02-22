package com.aquacarburos.rua.infrastructure.rest.handler;

import com.aquacarburos.rua.domain.model.exception.EmpresaNotFoundException;
import com.aquacarburos.rua.domain.model.exception.FormRuaNotFoundException;
import com.aquacarburos.rua.domain.model.exception.PagoPendienteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        log.warn("Intento de login con credenciales inválidas");
        return buildErrorResponse("UNAUTHORIZED", "Usuario o clave incorrectos", HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFound(UsernameNotFoundException ex) {
        log.warn("Usuario no encontrado: {}", ex.getMessage());
        return buildErrorResponse("UNAUTHORIZED", "Usuario o clave incorrectos", HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> handleDisabled(DisabledException ex) {
        log.warn("Cuenta deshabilitada: {}", ex.getMessage());
        return buildErrorResponse("FORBIDDEN", "La cuenta está deshabilitada", HttpStatus.FORBIDDEN, null);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentication(AuthenticationException ex) {
        log.warn("Error de autenticación: {}", ex.getMessage());
        return buildErrorResponse("UNAUTHORIZED", "Error de autenticación", HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(PagoPendienteException.class)
    public ResponseEntity<ErrorResponse> handlePagoPendiente(PagoPendienteException ex) {
        log.info("Acceso denegado por pago pendiente: {}", ex.getMessage());
        return buildErrorResponse("PAYMENT_REQUIRED", ex.getMessage(), HttpStatus.PAYMENT_REQUIRED, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        log.warn("Error de validación detectado: {} errores encontrados", ex.getBindingResult().getErrorCount());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));

        return buildErrorResponse("VALIDATION_ERROR", "Datos de entrada inválidos", HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(EmpresaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EmpresaNotFoundException ex) {
        log.info("Recurso no encontrado: {}", ex.getMessage());
        return buildErrorResponse("NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(FormRuaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(FormRuaNotFoundException ex) {
        log.info("Recurso no encontrado: {}", ex.getMessage());
        return buildErrorResponse("NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
        log.warn("Solicitud inválida: {}", ex.getMessage());
        return buildErrorResponse("VALIDATION_ERROR", ex.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {

        if (ex.getClass().getName().startsWith("org.springdoc") ||
                ex.getClass().getName().startsWith("org.springframework.web.servlet.resource")) {
            throw (RuntimeException) ex;
        }

        log.error("Excepción interna del servidor [500]: ", ex);

        return buildErrorResponse("INTERNAL_SERVER_ERROR",
                "Ocurrió un error inesperado en el servidor. Por favor, contacte al soporte.",
                HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String code, String message, HttpStatus status, Map<String, String> details) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(code)
                .message(message)
                .timestamp(LocalDateTime.now())
                .details(details)
                .build();
        return new ResponseEntity<>(errorResponse, status);
    }
}