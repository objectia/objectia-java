package com.objectia.exceptions;

/**
 * The APIConnectionException is thrown when the client cannot connect to the server.
 */
public class APIConnectionException extends APIException {

    private static final long serialVersionUID = 1L;

    /**
     * Class constructor.
     * 
     * @param message the error message
     */
    public APIConnectionException(String message) {
        super(message);
    }
}