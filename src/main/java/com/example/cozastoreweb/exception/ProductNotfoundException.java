package com.example.cozastoreweb.exception;

import com.example.cozastoreweb.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProductNotfoundException extends RuntimeException{
    String titlerError;
    String message;

    public String getTitlerError() {
        return titlerError;
    }

    public void setTitlerError(String titlerError) {
        this.titlerError = titlerError;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductNotfoundException(String message) {
        this.message = message;
    }

    public ProductNotfoundException(String titlerError, String message) {
        this.titlerError = titlerError;
        this.message = message;
    }

    public ProductNotfoundException(String message, String titlerError, String message1) {
        super(message);
        this.titlerError = titlerError;
        this.message = message1;
    }

    public ProductNotfoundException(String message, Throwable cause, String titlerError, String message1) {
        super(message, cause);
        this.titlerError = titlerError;
        this.message = message1;
    }

    public ProductNotfoundException(Throwable cause, String titlerError, String message) {
        super(cause);
        this.titlerError = titlerError;
        this.message = message;
    }

    public ProductNotfoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String titlerError, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.titlerError = titlerError;
        this.message = message1;
    }

}
