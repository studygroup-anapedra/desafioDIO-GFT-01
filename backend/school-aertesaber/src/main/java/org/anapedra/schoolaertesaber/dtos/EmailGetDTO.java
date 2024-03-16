package org.anapedra.schoolaertesaber.dtos;

import org.anapedra.schoolaertesaber.entities.Email;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.enums.EmailType;
import org.anapedra.schoolaertesaber.entities.enums.PhoneType;

import java.io.Serial;
import java.io.Serializable;

public class EmailGetDTO implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;


    private Long id;

    private String namePersonRegistration;

    private String number;
    private EmailType emailType;



    public EmailGetDTO(Email entity) {
        id = entity.getId();
        namePersonRegistration = entity.getRegistration().getFirstName();
        number = entity.getEmail();
        emailType = (entity.getEmailType() == null) ? null: entity.getEmailType();


    }

    public Long getId() {
        return id;
    }

    public String getNamePersonRegistration() {
        return namePersonRegistration;
    }

    public String getNumber() {
        return number;
    }

    public EmailType getEmailType() {
        return emailType;
    }
}
