package com.elreddit.controller;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.elreddit.constants.RedditConstants;
import com.elreddit.util.ServiceFactory;
import com.fasterxml.jackson.core.JsonProcessingException;

@Path("/search")
public class SearchController {
	
	@GET
	public Response searchAndSortByQuery(@QueryParam("q") String q) throws JsonProcessingException, IOException{
		String attrName = RedditConstants.AUTHOR_ATTRIBUTE; 
		String response = ServiceFactory.getSearchService().searchAndSortByQuery(q, attrName);
		
		if(response == null){
			return Response.status(Response.Status.BAD_REQUEST).entity("Something is not right!! Please Try Again").build();
		}else{
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}
}
