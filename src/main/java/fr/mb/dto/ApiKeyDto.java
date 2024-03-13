package fr.mb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.mb.entities.ClientEntity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ApiKeyDto {
    @JsonProperty(index = 1)
    private String apiKey;
    @JsonProperty(index =2)
    private int quota;

    public ApiKeyDto(ClientEntity clientEntity){
        this.apiKey = clientEntity.getApiKey();
        this.quota = clientEntity.getQuota();
    }
}
