package br.com.jettyrest.restful.resource;

import java.net.URI;
import java.net.URL;
import java.security.ProtectionDomain;

import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class JobQueueServer {

	private static final int SERVER_PORT = 8680;

	private JobQueueServer() {
	}

	public static void main(String[] args) throws Exception {
		URI baseUri = UriBuilder.fromUri("http://localhost").port(SERVER_PORT).build();
		ResourceConfig config = new ResourceConfig(UrlResource.class);
		Server server = JettyHttpContainerFactory.createServer(baseUri, config, false);

		ContextHandler contextHandler = new ContextHandler("/encurtador");
		contextHandler.setHandler(server.getHandler());

		ProtectionDomain protectionDomain = JobQueueServer.class.getProtectionDomain();
		URL location = protectionDomain.getCodeSource().getLocation();

		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setWelcomeFiles(new String[] { "index.html" });
		resourceHandler.setResourceBase(location.toExternalForm());
		System.out.println(location.toExternalForm());
		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.setHandlers(new Handler[] { resourceHandler, contextHandler, new DefaultHandler() });
		server.setHandler(handlerCollection);
		server.start();
		server.join();
	}
}
