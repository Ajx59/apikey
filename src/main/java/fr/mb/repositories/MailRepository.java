package fr.mb.repositories;

import fr.mb.dto.MailDto;
import fr.mb.entities.ClientEntity;
import fr.mb.entities.MailEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

@RequestScoped
public class MailRepository implements PanacheRepositoryBase<MailEntity, Integer> {
    ClientRepository clientRepository = new ClientRepository();

    public boolean verifieQuotaDepasser(String apiKey) {
        LocalDate dateActuel = LocalDate.now(); // date du jour
        int mois = dateActuel.getMonthValue(); // recupere le mois en int
        ClientEntity client = clientRepository.find("apiKey", apiKey).firstResult(); //recupere toute les info client
        int quota = client.getQuota(); // je recupere le quota client
        int nbreMailClientMoisEnCour = (int) this.find("client.idClient = ?1 and MONTH(dateEnvoi) = ?2", client.getIdClient(), mois).count();

        if(quota == 0){ return true; }
        if(nbreMailClientMoisEnCour < quota){ return true; }
        return false;

    }
    public void enregistrerMail(MailDto mailDto, ClientEntity client){
        MailEntity mail = new MailEntity(mailDto,client);
        this.persist(mail);
    }


}
