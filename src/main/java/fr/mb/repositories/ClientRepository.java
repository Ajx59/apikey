package fr.mb.repositories;

import fr.mb.entities.ClientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ClientRepository implements PanacheRepositoryBase<ClientEntity, Integer> {


    public ClientEntity findByApi(String apiKey) {
        ClientEntity client = new ClientEntity();
        client = find("apiKey", apiKey).firstResult();
       return client;
    }

    public boolean isCompteActif(String apiKey) {
        boolean isValid = false;
        ClientEntity client = find("apiKey", apiKey).firstResult();
        if (client.isCompteActif()) {
            isValid = true;
        }
        return isValid;
    }

    public ClientEntity clientByApiKey(String apiKey){
        ClientEntity client = find("apiKey", apiKey).firstResult();
        return client;
    }

}
