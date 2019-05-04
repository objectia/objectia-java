package com.objectia.exceptions;

/**
 * A Response exception is returned when the server returns a 400 or 500 http error.
 */
public class ResponseException extends APIException {

    private static final long serialVersionUID = 1L;

    private final int status;

    /**
     * Class constructor.
     * 
     * @param status the http status code, ie. 404
     * @param message the error message
     */
    public ResponseException(int status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * Gets the status code
     * 
     * @return the status code
     */
    public int getStatus() {
        return status;
    }
}