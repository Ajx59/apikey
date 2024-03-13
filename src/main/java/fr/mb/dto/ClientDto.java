package fr.mb.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String email;
    @JsonProperty(index = 2)
    private String nomClient;
    @JsonProperty(index = 3)
    private int quota;


    //Class qui permet de récupérer un client en base
    public ClientDto(ClientEntity clientEntity){
        email = clientEntity.getEmail();
        nomClient = clientEntity.getNomClient();
        quota = clientEntity.getQuota();
    }

    public ClientDto() {

    }

    public static List<ClientDto> toDtoList(List<ClientEntity> clientEntities){
        List<ClientDto> clientDtoList = new ArrayList<>();
        for(ClientEntity clientEntity : clientEntities)
            clientDtoList.add(new ClientDto(clientEntity));
        return clientDtoList;
    }


}
