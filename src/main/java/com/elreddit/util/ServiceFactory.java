package com.elreddit.util;

import com.elreddit.parser.RedditJsonTextParser;
import com.elreddit.request.URLRequestImpl;
import com.elreddit.service.JsonSearchService;

public final class ServiceFactory implements Cloneable{

	private static final JsonSearchService searchService;
	
	static{
		searchService = new JsonSearchService(new URLRequestImpl(), new RedditJsonTextParser());
	}
	
	private ServiceFactory(){}
	
	public static JsonSearchService getSearchService(){
		return searchService;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
}
