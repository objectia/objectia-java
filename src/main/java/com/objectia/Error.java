package com.objectia;

/**
 * Error response
 */
public class Error {
    private final int status;
    private final String message;
    private final boolean success;
    private final String code;

    /**
     * Class constructor
     */
    public Error() {
        this.status = 0;
        this.message = null;
        this.success = false;
        this.code = null;
    }

    /**
     * Gets the status code
     * 
     * @return a numeric HTTP status code
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Gets the error message
     * 
     * @return a string with the error message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the error code
     * 
     * @return a string with the error code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the success flag
     * 
     * @return true if successful, otherwise false
     */
    public boolean getSuccess() {
        return this.success;
    }
}
