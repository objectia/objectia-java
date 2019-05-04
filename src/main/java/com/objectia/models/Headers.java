package com.objectia.models;

import java.util.List;
import java.util.Map;

/**
 * HTTP headers
 */
public class Headers {

	private Map<String, List<String>> headers;

	/**
	 * Class constructor
	 * 
	 * @param headers a map of header key/value pairs.
	 */
	public Headers(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	/**
	 * Returns the first header value for a given key
	 * 
	 * @param name The name of the header key
	 * @return the first value for the given key
	 */
	public String get(String name) {
		List<String> valuesList = values(name);
		if (valuesList == null || valuesList.size() == 0) {
			return null;
		}
		return valuesList.get(0);
	}

	/**
	 * Returns a list of values for the given header
	 * 
	 * @param name the name of the header to retrieve
	 * @return a list of strings with header values
	 */
	public List<String> values(String name) {
		return headers == null ? null : headers.get(name);
	}
}