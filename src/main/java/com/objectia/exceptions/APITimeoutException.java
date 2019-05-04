package com.objectia.exceptions;

/**
 * The APITimeoutException is thrown when the request from the client to the server times out.
 */
public class APITimeoutException extends APIException {

    private static final long serialVersionUID = 1L;

    /**
     * Class constructor.
     * 
     * @param message the error message
     */
    public APITimeoutException(String message) {
        super(message);
    }
}