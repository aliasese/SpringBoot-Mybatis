package com.cnebula.ill.exception;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class ResponseVo<T> {
    //private static final long serialVersionUID = 1497778429907043244L;
    private final static ObjectMapper MAPPER = new ObjectMapper();

    private Boolean success;
    private Integer code;
    private String errorMessageCN;
    private String errorMessageEN;
    private T data;

    public ResponseVo() {
    }

    public ResponseVo(Boolean success, Integer code, String errorMessageCN, String errorMessageEN, T data) {
        this.success = success;
        this.code = code;
        this.errorMessageCN = errorMessageCN;
        this.errorMessageEN = errorMessageEN;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getErrorMessageCN() {
        return errorMessageCN;
    }

    public String getErrorMessageEN() {
        return errorMessageEN;
    }

    public T getData() {
        return data;
    }

    /*public ResponseVo getMessage() {
        *//*HashMap<String, Object> respMap = new HashMap<>();
        respMap.put("success", this.success);
        respMap.put("code", this.code);
        respMap.put("errorMessageCN", this.errorMessageCN);
        respMap.put("errorMessageEN", this.errorMessageEN);
        String responseVo = null;
        try {
            responseVo = MAPPER.writeValueAsString(respMap);
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
        return new ResponseVo();
    }*/

}
