package com.kiik.rs;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/*
 * Configura para os
 * pacotes de recursos 
 */

@ApplicationPath("resources")
public class Application extends ResourceConfig{
	
	public Application(){
		packages("com.kiik.rs");
	}

}
