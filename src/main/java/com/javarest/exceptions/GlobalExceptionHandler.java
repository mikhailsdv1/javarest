package com.javarest.exceptions;


import com.javarest.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(CommonException.class)
    public ResponseEntity<RestError> handleUnexpectedException(CommonException ex) {
        String errorMsg = (ex.getMessage() == null) ? "Internal server error" : ex.getMessage();
        log.error("Internal Server Error", ex);
        RestError error = new RestError();
        error.setMessage(ex.getMessage());
        error.setCode(ex.getCode());
        error.setDetails(ex.getDetails());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestError> handleUnexpectedException(Exception ex) {
        log.error("Internal Server Error", ex);
        RestError error = new RestError();
        error.setMessage("Internal Server Error");
        error.setCode(Constants.ERR_INT_SERVER_ERR);
        error.setDetails(ex.toString());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
