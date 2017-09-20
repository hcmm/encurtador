package br.com.jettyrest.restful.resource;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.validator.routines.UrlValidator;

import com.google.common.hash.Hashing;

import br.com.jettyrest.model.Url;

@Path("/url")
public class UrlResource {

	Response response;

	public static final HashMap<String, String> mapUrl = new HashMap<String, String>();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Url adicionarUrl(Url url) {
		System.out.println("Parametro do recebimento ajax:" + url);

		final UrlValidator urlValidator = new UrlValidator(new String[] { "http", "https" });

		if (urlValidator.isValid(url.getUrl())) {
			final String id = Hashing.murmur3_32().hashString(url.getUrl(), StandardCharsets.UTF_8).toString();

			mapUrl.put(id, url.getUrl());

			url.setUrlCurta("http://localhost:8680/encurtador/url/get/" + id);
			return url;
		} else
			return null;

	}

	@GET
	@Path("/get/{id}")
	public Response redirect(@PathParam("id") String urlCodificada) throws Exception {
		URI targetURIForRedirection = new URI(mapUrl.get(urlCodificada));
		return Response.seeOther(targetURIForRedirection).build();
	}

}
