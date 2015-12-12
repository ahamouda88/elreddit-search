package com.elreddit.request;

import java.net.URL;

/**
 * An interface represents the main methods for handling a URL request.
 */
public interface URLRequest {

	/**
	 * This method is used to send a request to a given URL, and return the response as a string.
	 * 
	 * @param url - the full url of the request.
	 * @return the http response string. Returns <b>null</b> if request is invalid.
	 * 
	 * @throws NullPointerException if given url is <b>null</b>.
	 */
	public String getHttpResponse(URL url);
}
