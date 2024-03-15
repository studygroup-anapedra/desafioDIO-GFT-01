package org.anapedra.schoolaertesaber.dtos;

import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.enums.PhoneType;

import java.io.Serial;
import java.io.Serializable;

public class PhoneGetDTO implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;


    private Long id;

    private String namePersonRegistration;

    private String number;
    private PhoneType phoneType;



    public PhoneGetDTO(Phone entity) {

        id = entity.getId();
        namePersonRegistration = entity.getRegistration().getFirstName();
        number = entity.getNumber();
        phoneType = (entity.getPhoneType() == null) ? null: entity.getPhoneType();


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

    public PhoneType getPhoneType() {
        return phoneType;
    }

}
