package fr.mb.resources;

import fr.mb.dto.ApiKeyDto;
import fr.mb.dto.MailHistoriqueDto;
import fr.mb.entities.ClientEntity;
import fr.mb.entities.MailEntity;
import fr.mb.repositories.ClientRepository;
import fr.mb.repositories.MailRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.time.LocalDateTime;

@Path("/apiKeys/")
@Tag(name = "ApiKeys")
@Produces(MediaType.APPLICATION_JSON)
public class apiKeyResource {
    @Inject
    ClientRepository clientRepository;
    @Inject
    MailRepository mailRepository;

    @GET
    @Operation(summary = "apiKey", description = "Rechercher une api Key")
    @APIResponse(responseCode = "200", description = "Ok, Api key found")
    @APIResponse(responseCode = "404", description = "Api Key not found")
    @Path("{apiKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApiKey(@PathParam("apiKey") String apiKey){
        ClientEntity client = clientRepository.findByApi(apiKey);
        if (client != null){
            ApiKeyDto utilisateurDto = new ApiKeyDto(client);
            return Response.ok(utilisateurDto).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Operation(summary = "Quota", description = "renvoit le quota du client")
    @APIResponse(responseCode = "200", description = "Ok, Quota recupéré")
    @APIResponse(responseCode = "404", description = "Client non trouvé")
    @Path("{apiKey}/quota")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getQuota(@PathParam("apiKey") String apiKey){
        ClientEntity client = clientRepository.findByApi(apiKey);
        if (client != null){
            int quota = mailRepository.countMailParMois(client.getIdClient());
            return Response.ok(quota).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "La ressource a été crée !")
    @APIResponse(responseCode = "500",  description = "Le serveur a rencontré un problème !")
    @Path("/{apiKey}/mail")
    @Operation(hidden = false)
    public Response addMail(MailHistoriqueDto mailHistoriqueDto, @PathParam("apiKey") String apiKey) {
        ClientEntity client = clientRepository.findByApi(apiKey);
        LocalDateTime now = LocalDateTime.now();
        MailEntity mail = new MailEntity(mailHistoriqueDto,client);
        mail.setClient(client);
        mail.setDestinataire(mailHistoriqueDto.getDestinataire());
        mail.setObjet(mailHistoriqueDto.getObjet());
        mail.setDateEnvoi(now);
        mail.setClient(client);
        mailRepository.persist(mail);
        return Response.ok(mailHistoriqueDto).build();
    }


//public Response countMail(MailDto mailDto)....

}
