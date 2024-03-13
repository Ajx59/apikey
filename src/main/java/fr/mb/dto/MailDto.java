package fr.mb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.mb.entities.ClientEntity;
import io.quarkus.arc.All;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MailDto {
    @JsonProperty(index = 1)
    private String destinataire;
    @JsonProperty(index = 2)
    private String objet;
    @JsonProperty(index = 3)
    private String body;

    public MailDto(ClientEntity clientEntity) {
        this.destinataire = clientEntity.getEmail();
    }

    public void mailClientCreated(String apiKey, MailDto mailDto){
        mailDto.setObjet("Création de votre clé API-KEY");
        mailDto.setBody( "Bonjour\n" +
                "Pour pouvoir utiliser notre service Mail, vous allez avoir besoin de votre clé Apikey: " + apiKey + "\n" +
                "Veuillez la saisir dans l'entête avec la  notion API-KEY\n"+
                "Cordialement");
    }
}
