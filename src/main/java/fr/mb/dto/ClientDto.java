package fr.mb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.mb.entities.ClientEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClientDto {
    @JsonProperty(index = 1)
    private Integer idClient;
    @JsonProperty(index = 2)
    private String apiKey;
    @JsonProperty(index = 3)
    private String email;
    @JsonProperty(index = 4)
    private String nomClient;
    @JsonProperty(index = 5)
    private int quota;
    @JsonProperty(index = 6)
    private boolean active;


    //Class qui permet de récupérer un client en base
    public ClientDto(ClientEntity clientEntity){
        idClient = clientEntity.getIdClient();
        apiKey = clientEntity.getApiKey();
        email = clientEntity.getEmail();
        nomClient = clientEntity.getNomClient();
        quota = clientEntity.getQuota();
        active = clientEntity.isCompteActif();
    }

    public static List<ClientDto> toDtoList(List<ClientEntity> clientEntities){
        List<ClientDto> clientDtoList = new ArrayList<>();
        for(ClientEntity clientEntity : clientEntities)
            clientDtoList.add(new ClientDto(clientEntity));
        return clientDtoList;
    }

}
