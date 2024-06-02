package com.edsoncosta.WinLink.utils.handler;

import com.edsoncosta.WinLink.utils.exception.generic.GenericErrorDTO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DuplicateKeyHandler {

    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericErrorDTO genericHandler(DuplicateKeyException ex){
        return new GenericErrorDTO( "Chave duplicada:: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
