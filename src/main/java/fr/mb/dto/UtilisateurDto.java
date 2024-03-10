package fr.mb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.mb.entities.ClientEntity;
import lombok.Getter;
import lombok.Setter;
//Class qui permet de créer un Client en base de donné
@Getter
@Setter
public class UtilisateurDto {
    @JsonProperty(index = 1)
    private String apiKey;
    @JsonProperty(index =2)
    private int quota;

    public UtilisateurDto(ClientEntity clientEntity){
        this.apiKey = clientEntity.getApiKey();
        this.quota = clientEntity.getQuota();
    }
}
