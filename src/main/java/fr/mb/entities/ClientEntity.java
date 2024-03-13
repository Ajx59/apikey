package fr.mb.entities;

import fr.mb.dto.ClientDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name ="CLIENT")
@Getter
@Setter
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID_CLIENT")
    private Integer idClient;
    @Column(name ="API_KEY")
    private String apiKey;
    @Column(name="EMAIL")
    private String email;
    @Column(name="NOM_CLIENT")
    private String nomClient;
    @Column(name = "QUOTA")
    private Integer quota;
    @Column(name= "ACTIVE")
    private boolean compteActif;



    public ClientEntity(ClientDto clientDto){
        this.email = clientDto.getEmail();
        this.nomClient = clientDto.getNomClient();
        this.quota = clientDto.getQuota();
    }

    public ClientEntity(){

    }
}
