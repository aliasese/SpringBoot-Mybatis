package com.cnebula.ill.exception;

import lombok.Getter;
import java.io.Serializable;

@Getter
public class SystemErrorException extends RuntimeException {

    private Integer code;
    private String errorMessageCN;
    private String errorMessageEN;


    public SystemErrorException() {
        //super();
        this.code = ResponseException.SystemError.getCode();
        this.errorMessageCN = ResponseException.SystemError.getErrorMessageCN();
        this.errorMessageEN = ResponseException.SystemError.getErrorMessageEN();
    }


    /*@Override
    public String getMessage() {
        return this.errorMessageCN;
    }*/
}
