package com.elreddit.test;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import com.elreddit.controller.SearchController;

public class SearchControllerTest extends JerseyTest{
	
    @Override
    protected Application configure() {
        return new ResourceConfig(SearchController.class);
    }
 
    @Test
    public void test() {
    	int expectedResult = Response.Status.OK.getStatusCode();
    	String query = "test";
    	Builder builder = target("search").queryParam("q", query).request();
        Response response = builder.get(Response.class);
        
        int actualResult = response.getStatus();
        Assert.assertEquals(expectedResult, actualResult);
    }

}
