package com.elreddit.test;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.elreddit.parser.JsonTextParser;
import com.elreddit.parser.RedditJsonTextParser;
import com.elreddit.request.URLRequestImpl;
import com.elreddit.service.JsonSearchService;

public class SearchServiceTest {
	
	private JsonSearchService searchService;
	private JsonTextParser textPareser;
	private String jsonText;
	private String query;

	@Before
	public void initTest(){
		textPareser = new RedditJsonTextParser();
		searchService = new JsonSearchService(new URLRequestImpl(), textPareser);
		
		query = "cats";
		jsonText = searchService.searchByQuery(query);

		Assert.assertNotNull(jsonText);	
	}
	
	@Test
	public void testSearchAndSortyBy() throws InterruptedException, IOException{		
		String attribute = "author";
		String sortedJsonText = searchService.searchAndSortByQuery(query, attribute);
		
		Assert.assertNotNull(sortedJsonText);
	}
}
