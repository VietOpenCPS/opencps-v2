package org.opencps.zalo.hook.exception;

public class APIException extends Exception {

    int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public APIException(int code, String message) {
        super(message);
        this.code = code;
    }

    public APIException() {
        super();
    }

    public APIException(Throwable e) {
        super(e);
    }

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable e) {
        super(message, e);
    }

    public String toString() {
        return "error: " + code + " | " + super.getMessage();
    }
}
