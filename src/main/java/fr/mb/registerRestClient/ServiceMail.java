package fr.mb.registerRestClient;

import fr.mb.dto.MailDto;
import fr.mb.security.ApiKey;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/mail")
@RegisterRestClient(baseUri = "http://localhost:8081")
public interface ServiceMail {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response envoyerMail(@HeaderParam("API-KEY") String apiKey, MailDto mailDto);
}
