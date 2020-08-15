package com.mecanica.controller;

import java.util.List;

import com.mecanica.application.errors.CustomErro;
import com.mecanica.application.exceptions.RegraBaseException;
import com.mecanica.application.exceptions.ValidacaoControllerBaseException;
import com.mecanica.application.validations.Validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import springfox.documentation.annotations.ApiIgnore;

public abstract class BaseController {

    @Autowired
    protected Validations validations;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiIgnore
    public Object handlerError(MethodArgumentNotValidException ex) {
        List<CustomErro> erros = validations.by(ex.getBindingResult().getTarget()).getErros();
        if (erros.isEmpty()) {
            return ResponseEntity.badRequest().body("Verifique os parametros obrigatórios");
        }
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ValidacaoControllerBaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiIgnore
    public ResponseEntity<Object> handlerError(ValidacaoControllerBaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiIgnore
    public ResponseEntity<Object> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getRootCause().getMessage());
    }

    @ExceptionHandler(RegraBaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiIgnore
    public ResponseEntity<Object> RegraBaseException(RegraBaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiIgnore
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage().replace("UUID", ""));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiIgnore
    public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body(ex.getRootCause().getMessage());
    }
}