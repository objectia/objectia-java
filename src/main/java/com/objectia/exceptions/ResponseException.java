package com.objectia.exceptions;

/**
 * A Response exception is returned when the server returns a 400 or 500 http error.
 */
public class ResponseException extends APIException {

    private static final long serialVersionUID = 1L;

    private final int status;
    private final String code;

    /**
     * Class constructor.
     * 
     * @param status the http status code, ie. 404
     * @param message the error message
     * @param code the error code
     */
    public ResponseException(int status, String message, String code) {
        super(message);
        this.status = status;
        this.code = code;
    }

    /**
     * Gets the status code
     * 
     * @return the status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Gets the error code
     * 
     * @return the errpr code
     */
    public String getCode() {
        return code;
    }    
}