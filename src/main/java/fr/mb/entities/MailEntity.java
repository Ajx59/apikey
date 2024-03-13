package fr.mb.entities;

import fr.mb.dto.MailHistoriqueDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name ="MAIL_HISTORIQUE")
@Getter
@Setter
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mail")
    private Integer idMail;
    @Column(name = "destinataire")
    private String destinataire;
    @Column(name = "objet")
    private String objet;
    @Column(name = "date_envoi")
    private LocalDateTime dateEnvoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private ClientEntity client;

    public MailEntity(MailHistoriqueDto mailHistoriqueDto, ClientEntity client) {
        this.destinataire = mailHistoriqueDto.getDestinataire();
        this.objet = mailHistoriqueDto.getObjet();
        this.dateEnvoi = LocalDateTime.now();
        this.client = client;
    }

    public MailEntity() {

    }
}
