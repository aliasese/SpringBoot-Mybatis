package com.cnebula.ill.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /*@Override
    public ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error("Error...................");
        return new ResponseEntity<Object>("Erroring**************", status);
    }*/

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVo handler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        LOGGER.info("Restful http request emerge exception");
        if (e instanceof RequestParamMissingException) {
            return ResponseUtil.requestParamMissing();
        } else if (e instanceof MissingRequestHeaderException){
            return ResponseUtil.requestHeaderMissing(e.getLocalizedMessage());
        } else if (e instanceof MissingPathVariableException) {
            return ResponseUtil.pathVariableMissing(e.getLocalizedMessage());
        }
        return ResponseUtil.systemError();
    }
}
