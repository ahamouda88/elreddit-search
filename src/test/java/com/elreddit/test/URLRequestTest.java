package com.elreddit.test;

import java.io.IOException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.elreddit.request.URLRequest;
import com.elreddit.request.URLRequestImpl;

public class URLRequestTest {

	private URLRequest search;
	
	@Before
	public void initTest(){
		search = new URLRequestImpl();
	}

	@Test
	public void testRandomURL() throws IOException{
		String urlString = "https://www.reddit.com/";
		URL url = new URL(urlString);
		String result = search.getHttpResponse(url);

		Assert.assertNotNull(result);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullException() throws IOException{
		search.getHttpResponse(null);
	}
}
