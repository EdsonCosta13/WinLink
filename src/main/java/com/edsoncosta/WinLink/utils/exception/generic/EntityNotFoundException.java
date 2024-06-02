package com.edsoncosta.WinLink.utils.exception.generic;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityNotFoundException extends RuntimeException{
    private String launcher;
    private String message;

    public EntityNotFoundException(String message){
        super(message);
        this.message = message;
    }

    public EntityNotFoundException(String message, String launcher){
        super(message);
        this.message = message;
        this.launcher = launcher;
    }
}
