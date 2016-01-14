package com.elreddit.service;

import com.elreddit.constants.RedditConstants;
import com.elreddit.parser.JsonTextParser;
import com.elreddit.request.URLRequest;
import com.elreddit.util.ParametersUtil;

/**
 * A class that extends the <b>{@link AbstSearchService}</b> abstract class, and used for parsing 
 * a Json text, and traverse the text using a <b>{@link JsonTextParser}</b>.
 */
public class JsonSearchService extends AbstSearchService{

	private final JsonTextParser jsonParser;
	
	public JsonSearchService(URLRequest urlRequest, JsonTextParser jsonParser){
		super(urlRequest);
		this.jsonParser = jsonParser;
	}

	/**
	 * This method is used to search comments, and return a Json text sorted by a given attribute.
	 * 
	 * @param q - query parameter value.
	 * @param attrName - attribute name.
	 * @return Json text after sorting. Returns <b>null</b> if input content is invalid.
	 * 
	 * @throws NullPointerException if any given parameter is <b>null</b>.
	 */
	public String searchAndSortByQuery(String q, String attrName) {
		ParametersUtil.checkNullParameters(q, attrName);
		
		String result = null;
		synchronized(mutex){
			String response = this.searchByQuery(q);			
			if(response != null){			
				result = jsonParser.sortTextBy(response, attrName);
			}		
		}
		return result;
	}
	
	public String getURLPath(String para){
		StringBuilder sb = new StringBuilder();		
		sb.append(RedditConstants.JSONSEARCH_URL).append(RedditConstants.SEARCH_QUERY).append(para);
		return sb.toString();
	}
	
	public JsonTextParser getJsonParser() {
		return jsonParser;
	}
	
}
