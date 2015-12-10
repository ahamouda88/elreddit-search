package com.elreddit.request;

import java.io.IOException;
import java.net.URL;

/**
 * An interface represents the main methods for searching Reddit Comments.
 */
public interface URLRequest {

	/**
	 * This method is used to send a request to a give URL, and return the response as a string.
	 * 
	 * @param url - the url.
	 * @return the http response string.
	 * @throws IOException - if an I/O exception occurs.
	 */
	public String getHttpResponse(URL url) throws IOException;
}
