package com.cnebula.ill.exception;

import java.io.Serializable;

public class RequestParamMissingException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 802099872678885373L;
    private Integer code;
    private String errorMessageCN;
    private String errorMessageEN;


    public RequestParamMissingException() {
        super();
        this.code = ResponseException.RequestParamMissing.getCode();
        this.errorMessageCN = ResponseException.RequestParamMissing.getErrorMessageCN();
        this.errorMessageEN = ResponseException.RequestParamMissing.getErrorMessageEN();
    }

    @Override
    public String getMessage() {
        return this.errorMessageCN;
    }
}
