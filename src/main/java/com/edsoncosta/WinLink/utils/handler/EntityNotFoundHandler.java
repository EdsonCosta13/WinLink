package com.edsoncosta.WinLink.utils.handler;

import com.edsoncosta.WinLink.utils.exception.generic.EntityNotFoundException;
import com.edsoncosta.WinLink.utils.exception.generic.GenericErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityNotFoundHandler {
    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericErrorDTO genericHandler(EntityNotFoundException ex){
        return new GenericErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
