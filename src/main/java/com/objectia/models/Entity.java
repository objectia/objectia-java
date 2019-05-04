package com.objectia.models;

/**
 * Entity generic base class
 */
public class Entity<T> {
	protected int status;
	protected String message;
    protected boolean success;
    protected T data;

    /**
     * Class constructor
     */
    public Entity() {
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
     * Gets the success flag
     * 
     * @return true if successful, otherwise false
     */
    public boolean getSuccess() {
        return this.success;
    }

    /** 
     * Gets the data model object
     * 
     * @return a model object
     */
    public T getData() {
        return this.data;
    }

}