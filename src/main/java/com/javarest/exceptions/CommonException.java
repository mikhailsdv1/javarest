package com.javarest.exceptions;

public class CommonException extends RuntimeException {

    private String code;

    private String details;

    public CommonException(String code, String message, String details) {
        super(message);
        this.code = code;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
