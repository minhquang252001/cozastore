package com.example.cozastoreweb.payload.response;

import org.springframework.http.HttpStatus;

public class BaseResponse {
    private int statuscode=200;
    private String message="";
    private Object data;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}