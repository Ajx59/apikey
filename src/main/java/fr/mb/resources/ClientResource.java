package fr.mb.resources;

import fr.mb.dto.ClientDto;
import fr.mb.dto.MailDto;
import fr.mb.dto.UtilisateurDto;
import fr.mb.entities.ClientEntity;
import fr.mb.entities.MailEntity;
import fr.mb.registerRestClient.ServiceMail;
import fr.mb.repositories.ClientRepository;
import fr.mb.repositories.MailRepository;
import fr.mb.security.ApiKey;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.awt.*;
import java.util.List;

@Path("/Clients/")
@Tag(name = "Client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientResource {
    @Inject
    ClientRepository clientRepository;
    @RestClient
    ServiceMail serviceMail;
    @Inject
    MailRepository mailRepository;
    @GET
    public Response getAll(){
        List<ClientEntity> clientEntities = clientRepository.listAll();
        return Response.ok(ClientDto.toDtoList(clientEntities)).build();
    }
    @GET
    @Operation(summary = "Client by id", description = "Search a client by its ID")
    @APIResponse(responseCode = "200", description = "Ok, client found")
    @APIResponse(responseCode = "404", description = "Color not found")
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id){
        ClientEntity client = clientRepository.findById(id);
        if(client == null)
            return Response.status(404, "Cet identifiant n'existe pas").build();
        return Response.ok(client).build();
    }

    @Transactional
    @POST
    @Operation(summary = "Créer un utilisateur", description = "Création d'un utilisateur")
    @APIResponse(responseCode = "201", description = "La ressource a été crée !")
    @APIResponse(responseCode = "500",  description = "Le serveur a rencontré un problème !")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(@QueryParam("Email") String email,@QueryParam("Nom Client")String nomClient, @QueryParam("Quota Email") Integer quota, @Context UriInfo uriInfo){
        ClientEntity client = new ClientEntity();
        client.setApiKey(ApiKey.generateKey());
        client.setEmail(email);
        client.setNomClient(nomClient);
        client.setQuota(quota);
        client.setCompteActif(true);
        clientRepository.persist(client);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(client.getIdClient().toString());
        //bloque la création
       /* MailDto mailDto = new MailDto(client.getEmail(), "Service Api Key", "Voici votre clé api," + client.getApiKey() + " Enregistrer la et ne la perdez pas !");
        serviceMail.envoyerMail("azerty", mailDto);*/
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "La ressource a été crée !")
    @APIResponse(responseCode = "500",  description = "Le serveur a rencontré un problème !")
    @Path("/{id}/mail")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmail(@PathParam("id")Integer id, MailDto mailDto) {
        ClientEntity client = clientRepository.findById(id);
        if(client == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        MailEntity mail = new MailEntity(mailDto, client);
        mailRepository.persist(mail);
        return Response.ok().build();
    }

    @Transactional
    @PUT
    @Path("/{id}/newKey")
    @Operation(summary = "New apiKey", description = "Générer une nouvelle apiKey")
    @APIResponse(responseCode = "201", description = "L'Api a été modifié avec succès")
    @APIResponse(responseCode = "404", description = "Le client n'a pas été trouvée")
    public Response updateApiKey(@PathParam("id") Integer id){
        ClientEntity client = clientRepository.findById(id);
        if(client == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        client.setApiKey(ApiKey.generateKey());
        clientRepository.persist(client);
        return Response.ok(client).build();
    }

    @Transactional
    @PUT
    @Path("/{id}")
    @Operation(summary = "New quota", description = "Modifier le quota")
    @APIResponse(responseCode = "201", description = "Le quota a été modifié avec succès")
    @APIResponse(responseCode = "404", description = "Le client n'a pas été trouvée")
    public Response updateQuota(@PathParam("id") Integer id,@QueryParam("Nouveau Quota") Integer newQuota){
        ClientEntity client = clientRepository.findById(id);
        if(client == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        client.setQuota(newQuota);
        clientRepository.persist(client);
        return Response.ok(client).build();
    }
}
