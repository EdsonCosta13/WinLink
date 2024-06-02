package com.edsoncosta.WinLink.utils.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PreconditionException extends RuntimeException {
    private String launcher;
    private String message;

    public PreconditionException(String message){
        super(message);
        this.message = message;
    }

    public PreconditionException(String message, String launcher){
        this(message);
        this.launcher = launcher;
    }
}
