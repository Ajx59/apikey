package fr.mb.registerRestClient;

import fr.mb.dto.MailDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

@Path("/mail")
@RegisterRestClient(baseUri = "http://localhost:8080")
public interface ServiceMail {
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response envoyerMail(@HeaderParam("API-KEY") String apiKey, MailDto mailDto);
}
