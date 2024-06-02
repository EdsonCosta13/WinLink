package com.edsoncosta.WinLink.utils.exception.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenericException extends RuntimeException {
    private String launcher;
    private String message;

    public GenericException(String message){
        super(message);
        this.message = message;
    }

    public GenericException(String message, String launcher){
        super(message);
        this.message = message;
        this.launcher = launcher;
    }
}
