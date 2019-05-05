package com.objectia.models;

import java.util.List;
import java.util.Map;

/**
 * HTTP Response
 */
public class Response {
	private int status;
	private String body;
	private Headers headers;
	
	/**
	 * Class constructor
	 * 
	 * @param status the HTTP status code
	 * @param body the HTTP text body
	 */
	public Response(int status, String body) {
		this.status = status;
		this.body = body;
		this.headers = null;
	}

	/**
	 * Class constructor
	 * 
	 * @param status the HTTP status code
	 * @param body the HTTP text body
	 * @param headers a list of HTTP headers
	 */
	public Response(int status, String body, Map<String, List<String>> headers) {
		this.status = status;
		this.body = body;
		this.headers = new Headers(headers);
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
     * Gets the text body
     * 
     * @return a string containing the body of the HTTP response
     */
	public String getBody() {
		return this.body;
	}

    /**
     * Gets the headers of the HTTP response
     * 
     * @return a Headers object
     */
	public Headers getHeaders() {
		return headers;
	}
}