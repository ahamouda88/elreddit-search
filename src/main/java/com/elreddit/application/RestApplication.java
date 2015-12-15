package com.elreddit.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * A class that replaces the <b>web.xml</b> file, by using the {@link ApplicationPath} annotation.
 */
@ApplicationPath("/elreddit")
public class RestApplication extends ResourceConfig {
	
	private final String controllerPackage = "com.elreddit.controller";
	
    public RestApplication() {
        packages(controllerPackage);
    }
    
}