package com.elreddit.util;

import com.elreddit.parser.RedditJsonTextParser;
import com.elreddit.request.URLRequestImpl;
import com.elreddit.service.JsonSearchService;

public final class ServiceFactory {

	private static final JsonSearchService searchService;
	
	static{
		searchService = new JsonSearchService(new URLRequestImpl(), new RedditJsonTextParser());
	}
	
	private ServiceFactory(){}
	
	public static JsonSearchService getSearchService(){
		return searchService;
	}
}
