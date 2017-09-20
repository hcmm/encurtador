package br.com.jettyrest.restful.resource;

import org.glassfish.jersey.server.ResourceConfig;

public class EncurtadorApp extends ResourceConfig {

	public EncurtadorApp() {
		packages("br.com.jettyrest.restful.resource");
	}
}
