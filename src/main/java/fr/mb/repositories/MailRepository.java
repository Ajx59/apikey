package fr.mb.repositories;

import fr.mb.entities.MailEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.time.LocalDate;

@RequestScoped
public class MailRepository implements PanacheRepositoryBase<MailEntity, Integer> {
    @Inject
    ClientRepository clientRepository;

    public int countMailParMois(Integer idclient) {
        LocalDate dateActuel = LocalDate.now();
        int mois = dateActuel.getMonthValue();
        Integer nbreMailClientMoisEnCour = Math.toIntExact(this.find("client.idClient = ?1 and MONTH(dateEnvoi) = ?2", idclient, mois).count());
        return nbreMailClientMoisEnCour;
    }












}
