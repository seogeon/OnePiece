package com.hanaset.onepiecezoro.web.exception;

public class ZoroException extends RuntimeException {

    String code;

    public String getCode() {
        return code;
    }

    public ZoroException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
