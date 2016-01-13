package com.elreddit.service;

import java.net.MalformedURLException;
import java.net.URL;

import com.elreddit.request.URLRequest;
import com.elreddit.util.ParametersUtil;

/**
 * An abstract class that represents main methods for performing search operations.
 */
public abstract class AbstSearchService {

	private final URLRequest urlRequest;
	protected final Object mutex = new Object();
	
	/*
	 * Inject the URL Request into the Search Service.
	 */
	public AbstSearchService(URLRequest urlRequest){
		this.urlRequest = urlRequest;
	}
	
	/**
	 * This method is used to search comments given the value of the query parameter.
	 * 
	 * @param q - query parameter.
	 * @return a string that represents the search response. Returns <b>null</b> if search wasn't successful.
	 * 
	 * @throws NullPointerException if given query is <b>null</b>.
	 */
	public String searchByQuery(String q) {
		ParametersUtil.checkNullParameters(q);
		
		String result = null;
		String urlString = getURLPath(q);
		try {
			URL url = new URL(urlString);
			synchronized(mutex){
				result = urlRequest.getHttpResponse(url);
			}
		} catch (MalformedURLException ex) {
			System.out.println("AbstSearchService.searchByQuery - MalformedURLException: " + ex.getMessage());
		}			
		return result;
	}
	
	/**
	 * This method is used to get the full path URL of the search request, after adding the parameter.
	 * 
	 * @param para - query parameter.
	 * @return the full URL path.
	 */
	public abstract String getURLPath(String para);
	
	public URLRequest getURLRequest() {
		return urlRequest;
	}
}
