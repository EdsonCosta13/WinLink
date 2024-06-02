package com.edsoncosta.WinLink.utils.handler.generic;


import com.edsoncosta.WinLink.utils.exception.PreconditionException;
import com.edsoncosta.WinLink.utils.exception.generic.GenericErrorDTO;
import com.edsoncosta.WinLink.utils.exception.generic.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GenericHandler {

    @ResponseBody
    @ExceptionHandler(GenericException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericErrorDTO handler(GenericException ex){
        return new GenericErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericErrorDTO handlerRuntime(RuntimeException ex){
        return new GenericErrorDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(PreconditionException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public GenericErrorDTO preconditionFailHandler(PreconditionException ex){
        return new GenericErrorDTO(ex.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }
}
