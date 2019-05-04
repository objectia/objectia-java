package com.objectia.exceptions;

/**
 * The APIException is the abstract base class for all exceptions thrown by the REST client.
 */
public abstract class APIException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Class constructor.
     * 
     * @param message the error message
     */
    public APIException(String message) {
        super(message, null);
    }
}