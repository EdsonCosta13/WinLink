package com.edsoncosta.WinLink.utils.http;

import lombok.Getter;
import lombok.Setter;
import lombok.With;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class Response<T>{

    private String message;
    private HttpStatus status;
    private Integer code;
    @Setter
    @With
    private T data;
    private final LocalDateTime timestamp = LocalDateTime.now();

    public Response(T data){
        this.data = data;
    }
    public Response(){}
    public Response<T> withData(T data){
        this.data = data;
        return this;
    }

    public  Response<T> success(final String message){
        this.status = HttpStatus.OK;
        this.code = HttpStatus.OK.value();
        this.message = message;
        return this;
    }
    public  Response<T>  error(final String message){
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = message;
        return  this;
    }
    public  Response<T> notFound(final String message){
        this.status = HttpStatus.NOT_FOUND;
        this.code = HttpStatus.NOT_FOUND.value();
        this.message = message;
        return  this;
    }
    public  Response<T> forbidden(final String message){
        this.status = HttpStatus.FORBIDDEN;
        this.code = HttpStatus.FORBIDDEN.value();
        this.message = message;
        return this;
    }

    public  Response<T> notAcceptable(final String message){
        this.status = HttpStatus.NOT_ACCEPTABLE;
        this.code = HttpStatus.NOT_ACCEPTABLE.value();
        this.message = message;
        return this;
    }
    public Response<T> created(final String message){
        this.status = HttpStatus.CREATED;
        this.code = HttpStatus.CREATED.value();
        this.message = message;
        return this;
    }

    public ResponseEntity<Response<T>> toResponseEntity(){
        return ResponseEntity.status(this.status).body(this);
    }
}
