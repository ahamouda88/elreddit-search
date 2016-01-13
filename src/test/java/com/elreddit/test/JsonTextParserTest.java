package com.elreddit.test;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.elreddit.parser.JsonTextParser;
import com.elreddit.parser.RedditJsonTextParser;

public class JsonTextParserTest {
	
	private JsonTextParser textPareser;
	private String attrName;
	
	@Before
	public void initTest(){
		textPareser = new RedditJsonTextParser();
		attrName = "company";
	}
	
	@Test
	public void testSortyBy() throws InterruptedException, IOException{
		String json = "{\"data\":{\"children\":[{\"company\": \"Paypal\"},{\"company\": \"Google\"},{\"company\": \"Apple\"}]}}";
		
		String expectedResult = "{\"data\":{\"children\":[{\"company\":\"Apple\"},{\"company\":\"Google\"},{\"company\":\"Paypal\"}]}}";		
		String actualResult = textPareser.sortTextBy(json, attrName);
		
		Assert.assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testSortyByTwo() throws InterruptedException, IOException{
		String json = "{\"data\":{\"children\":[{\"company\": \"Paypal\", \"ceo\":\"Ahmed\"},{\"company\": \"Google\", \"ceo\":\"Ziad\"},{\"company\": \"Apple\", \"ceo\":\"Mike\"}]}}";
		attrName = "ceo";
		
		String expectedResult = "{\"data\":{\"children\":[{\"company\":\"Paypal\",\"ceo\":\"Ahmed\"},{\"company\":\"Apple\",\"ceo\":\"Mike\"},{\"company\":\"Google\",\"ceo\":\"Ziad\"}]}}";		
		String actualResult = textPareser.sortTextBy(json, attrName);

		Assert.assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void testSortyByThree() throws InterruptedException, IOException{
		String json = "{\"data\":{\"children\":[{\"company\": \"Paypal\"},{\"company\": \"about\"},{\"company\": \"Google\"},{\"company\": \"Apple\"}]}}";
		
		String expectedResult = "{\"data\":{\"children\":[{\"company\":\"about\"},{\"company\":\"Apple\"},{\"company\":\"Google\"},{\"company\":\"Paypal\"}]}}";		
		String actualResult = textPareser.sortTextBy(json, attrName);
		
		Assert.assertEquals(expectedResult, actualResult);
	}
}
