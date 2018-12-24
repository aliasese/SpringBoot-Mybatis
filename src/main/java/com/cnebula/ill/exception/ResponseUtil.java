package com.cnebula.ill.exception;

public class ResponseUtil {
    public static ResponseVo ok(Object data) {
        return new ResponseVo(true, 200, null, null, data);
    }

    public static ResponseVo systemError() {
        return new ResponseVo(false, ResponseException.SystemError.getCode(), ResponseException.SystemError.getErrorMessageCN(), ResponseException.SystemError.getErrorMessageEN(), null);
    }

    public static ResponseVo serviceUnavailable() {
        return new ResponseVo(false, ResponseException.ServiceUnavailable.getCode(), ResponseException.ServiceUnavailable.getErrorMessageCN(), ResponseException.ServiceUnavailable.getErrorMessageEN(), null);
    }

    public static ResponseVo resourceNotFound() {
        return new ResponseVo(false, ResponseException.ResourceNotFound.getCode(), ResponseException.ResourceNotFound.getErrorMessageCN(), ResponseException.ResourceNotFound.getErrorMessageEN(), null);
    }

    public static ResponseVo requestHeaderMissing(String message) {
        return new ResponseVo(false, ResponseException.RequestHeaderMissing.getCode(), ResponseException.RequestHeaderMissing.getErrorMessageCN(), String.format(ResponseException.RequestHeaderMissing.getErrorMessageEN(), message), null);
    }

    public static ResponseVo requestParamMissing() {
        return new ResponseVo(false, ResponseException.RequestParamMissing.getCode(), ResponseException.RequestParamMissing.getErrorMessageCN(), ResponseException.RequestParamMissing.getErrorMessageEN(), null);
    }

    public static ResponseVo pathVariableMissing(String message) {
        return new ResponseVo(false, ResponseException.PathVariableMissing.getCode(), ResponseException.PathVariableMissing.getErrorMessageCN(), String.format(ResponseException.PathVariableMissing.getErrorMessageEN(), message), null);
    }

    public static ResponseVo requestBodyError() {
        return new ResponseVo(false, ResponseException.RequestBodyError.getCode(), ResponseException.RequestBodyError.getErrorMessageCN(), ResponseException.RequestBodyError.getErrorMessageEN(), null);
    }

    public static ResponseVo requestForbidden() {
        return new ResponseVo(false, ResponseException.RequestForbidden.getCode(), ResponseException.RequestForbidden.getErrorMessageCN(), ResponseException.RequestForbidden.getErrorMessageEN(), null);
    }
}
