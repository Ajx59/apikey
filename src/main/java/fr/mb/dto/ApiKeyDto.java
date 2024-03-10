package fr.mb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.mb.entities.ClientEntity;
import fr.mb.security.ApiKey;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiKeyDto {
    @JsonProperty(index = 1)
    private String apiKey;
    @JsonProperty(index = 2)
    private int quota;

    public ApiKeyDto(ClientEntity clientEntity){
            this.apiKey = clientEntity.getApiKey();
            this.quota = clientEntity.getQuota();
    }

}

