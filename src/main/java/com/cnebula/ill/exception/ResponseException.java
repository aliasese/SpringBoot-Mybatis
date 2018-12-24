package com.cnebula.ill.exception;

public enum ResponseException {
    SystemError(100500,"系统错误","System is error"),
    ServiceUnavailable(100501,"服务不可用","Service is unavailable"),
    ResourceNotFound(100404,"资源不存在","Resource is not found"),
    RequestHeaderMissing(100405,"请求头缺失","Request header is missing [%s]"),
    RequestParamMissing(100400,"请求参数缺失","Request param(s) is(are) missing [%s]"),
    PathVariableMissing(100401,"路径参数缺失","Path variable(s) is(are) missing [%s]"),
    RequestBodyError(100402,"请求体错误","Request body is error [%s]"),
    RequestForbidden(100403,"请求被拒绝","Request is forbidden");
    private Integer code;
    private String errorMessageCN;
    private String errorMessageEN;

    ResponseException(Integer code, String errorMessageCN, String errorMessageEN) {
        this.code = code;
        this.errorMessageCN = errorMessageCN;
        this.errorMessageEN = errorMessageEN;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessageCN() {
        return errorMessageCN;
    }

    public void setErrorMessageCN(String errorMessageCN) {
        this.errorMessageCN = errorMessageCN;
    }

    public String getErrorMessageEN() {
        return errorMessageEN;
    }

    public void setErrorMessageEN(String errorMessageEN) {
        this.errorMessageEN = errorMessageEN;
    }
}
