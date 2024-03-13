package fr.mb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailHistoriqueDto {
    @JsonProperty(index = 1)
    private String destinataire;
    @JsonProperty(index = 2)
    private String objet;
}
