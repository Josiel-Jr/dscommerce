package com.example.dscommerce_new.config.exceptions;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionApiHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<Object> handleNotFound(EntidadeNaoEncontradaException ex) {
        ApiErroResponse apiErroResponse = new ApiErroResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErroResponse);
    }

    @ExceptionHandler({IdAtribuidaComEntidade.class, RoleAdmin.class})
    public ResponseEntity<Object> handleBadRequest(Exception ex) {
        ApiErroResponse apiErroResponse = new ApiErroResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErroResponse);
    }

    @ExceptionHandler({UsernameAtribuidoComEntidade.class})
    public ResponseEntity<Object> handleExceptionInternal(UsernameAtribuidoComEntidade ux) {
        ApiErroResponse apiErroResponse = new ApiErroResponse(ux.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErroResponse);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ApiErroResponse appiErroResponse = new ApiErroResponse(ex.getMessage(), statusCode.value(), LocalDateTime.now());
        return ResponseEntity.status(statusCode).body(appiErroResponse);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorsMessage = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorsMessage.add(fieldError.getDefaultMessage());
        });
        ApiErrors apiErrorBody = new ApiErrors(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), errorsMessage);
        return ResponseEntity.status(status).body(apiErrorBody);
    }

    @Override
    public ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        List<String> errorsMessage = new ArrayList<>();
        ex.getAllValidationResults().forEach(parameterValidationResult -> {
            parameterValidationResult.getResolvableErrors().forEach(messageSourceResolvable -> {
                errorsMessage.add(messageSourceResolvable.getDefaultMessage());
            });
        });
        ApiErrors apiErrors = new ApiErrors(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), errorsMessage);
        return ResponseEntity.status(status).body(apiErrors);
    }

}
