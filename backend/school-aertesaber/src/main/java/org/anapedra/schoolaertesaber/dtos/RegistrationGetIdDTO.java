package org.anapedra.schoolaertesaber.dtos;

import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationGetIdDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;

    private RegistrationType registrationType;
    private String firstName;
    private String lastName;

    private String cpf;
    private String registrationEmail;
    private LocalDate dateBirth;
    private String registrationPhone;
    private String profession;
    private String imgUrl;
    private Instant registrationAt;
    private  Instant update;

    private List<PhoneGetDTO> phones =new ArrayList<>();


    public RegistrationGetIdDTO(Registration entity) {
        id = entity.getId();
        registrationType =(entity.getRegistrationType() == null) ? null: entity.getRegistrationType();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        cpf = entity.getCpf();
        registrationEmail = entity.getRegistrationEmail();
        dateBirth = entity.getDateBirth();
        registrationPhone = entity.getRegistrationPhone();
        profession = entity.getProfession();
        imgUrl = entity.getImgUrl();
        registrationAt=Instant.now();
        update=entity.getRegistrationAt();
        entity.getPhones().forEach(phone -> this.phones.add(new PhoneGetDTO(phone)));


    }

    public Long getId() {
        return id;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRegistrationEmail() {
        return registrationEmail;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public String getRegistrationPhone() {
        return registrationPhone;
    }

    public String getProfession() {
        return profession;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Instant getRegistrationAt() {
        return registrationAt;
    }

    public Instant getUpdate() {
        return update;
    }

    public List<PhoneGetDTO> getPhones() {
        return phones;
    }
}
