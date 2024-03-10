package fr.mb.resources;

import fr.mb.dto.ClientDto;
import fr.mb.dto.UtilisateurDto;
import fr.mb.entities.ClientEntity;
import fr.mb.repositories.ClientRepository;
import fr.mb.security.ApiKey;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/apiKeys/")
@Tag(name = "ApiKeys")
@Produces(MediaType.APPLICATION_JSON)
public class apiKeyResource {
    @Inject
    ClientRepository clientRepository;
    @GET
    @Operation(summary = "apiKey", description = "Rechercher une api Key")
    @APIResponse(responseCode = "200", description = "Ok, Api key found")
    @APIResponse(responseCode = "404", description = "Api Key not found")
    @Path("{apiKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApiKey(@PathParam("apiKey") String apiKey){
        ClientEntity client = clientRepository.findByApi(apiKey);
        if (client != null){
            UtilisateurDto utilisateurDto = new UtilisateurDto(client);
            return Response.ok(utilisateurDto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }






}
