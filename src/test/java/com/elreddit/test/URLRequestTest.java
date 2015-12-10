package com.elreddit.test;

import java.io.IOException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.elreddit.constants.URLConstants;
import com.elreddit.request.URLRequest;
import com.elreddit.request.URLRequestImpl;

public class URLRequestTest {

	private URLRequest search;
	
	@Before
	public void initTest(){
		search = new URLRequestImpl();
	}
	@Test
	public void testGetHttpResponse() throws IOException{
		String query = "test";
		String urlString = getURLString(query);		
		URL url = new URL(urlString);
		String result = search.getHttpResponse(url);
		
		Assert.assertNotNull(result);		
	}
	
	@Test
	public void testRandowURL() throws IOException{
		String urlString = "https://www.google.com/";
		URL url = new URL(urlString);
		String result = search.getHttpResponse(url);
		System.out.println(result);
		Assert.assertNotNull(result);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullException() throws IOException{
		search.getHttpResponse(null);
	}
	
	private String getURLString(String para){
		StringBuilder sb = new StringBuilder();
		
		sb.append(URLConstants.REDDIT_JSONSEARCH_URL);
		sb.append(URLConstants.REDDIT_SEARCH_QUERY);
		sb.append(para);
		return sb.toString();
	}
}
