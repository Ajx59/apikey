package fr.mb.entities;

import fr.mb.dto.MailDto;
import jakarta.persistence.*;
import jakarta.ws.rs.client.Client;
import lombok.*;

import java.time.LocalDate;

@Entity(name ="MAIL")
@Getter
@Setter
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mail")
    private Integer idMail;
    @Column(name = "destinataton")
    private String destinataire;
    @Column(name = "objet")
    private String objet;
    @Column(name = "date_envoi")
    private LocalDate dateEnvoi;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private ClientEntity client;

    public MailEntity(MailDto mailDto, ClientEntity client) {
        this.destinataire = mailDto.getDestinataire();
        this.objet = mailDto.getObjet();
        this.dateEnvoi = LocalDate.now();
        this.client = client;
    }

    public MailEntity() {

    }
}
