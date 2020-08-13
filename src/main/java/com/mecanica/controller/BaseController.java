package com.mecanica.controller;

import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.application.validation.Validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class BaseController {

    @Autowired
    protected Validations validations;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handlerError(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(validations.by(ex.getBindingResult().getTarget()).getErros());
    }

    @ExceptionHandler(ValidacaoControllerBaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handlerError(ValidacaoControllerBaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}