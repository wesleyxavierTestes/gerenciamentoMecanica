package com.mecanica.controller;

import com.mecanica.application.validation.Validations;
import com.mecanica.utils.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class BaseController {

    @Autowired
    protected Validations validations;
    
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public Object handlerError(MethodArgumentNotValidException ex) {
    //     return ex.getBindingResult().getAllErrors().stream()
    //     .map(c -> new ErrorMessage(c.getCodes()[0], c.getDefaultMessage()));
    // }
}