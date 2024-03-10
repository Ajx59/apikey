package fr.mb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class MailDto {

    private String destinataire;
    private String objet;
    private String body;


    /*private String destinataire;
    private String nomDestinataire;
    private String objet;
    private boolean mailType;
    private String nomMailType;
    private String body;
    private String lien;
    private String signature;*/
}
