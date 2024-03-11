package org.anapedra.schoolaertesaber.dtos;

import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.enums.PhoneType;

import java.io.Serial;
import java.io.Serializable;


public class PhoneDTO implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    private Long id;

    private String number;
    private PhoneType phoneType;



    public PhoneDTO() {

    }

    public PhoneDTO(Long id, String number, PhoneType phoneType) {
        this.id = id;
        this.number = number;
        this.phoneType = phoneType;

    }

    public PhoneDTO(Phone entity) {
        id = entity.getId();
        number = entity.getNumber();
        phoneType = (entity.getPhoneType() == null) ? PhoneType.PERSONAL_PHONE : entity.getPhoneType();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

}
