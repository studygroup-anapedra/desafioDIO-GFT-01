package org.anapedra.schoolaertesaber.dtos;

import jakarta.validation.constraints.NotEmpty;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

public class RegistrationMinDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;

    private RegistrationType registrationType;
    private String firstName;
    private String lastName;

    private String cpf;
    private String registrationEmail;
    private String registrationPhone;
    private String profession;
    private String imgUrl;
    private  Instant registrationAt;



    public RegistrationMinDTO(Registration entity) {
        id = entity.getId();
        registrationType =(registrationType == null) ? null: entity.getRegistrationType();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        cpf = entity.getCpf();
        registrationEmail = entity.getRegistrationEmail();
        registrationPhone = entity.getRegistrationPhone();
        profession = entity.getProfession();
        imgUrl = entity.getImgUrl();
        registrationAt = entity.getRegistrationAt();




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

    public void setRegistrationAt(Instant registrationAt) {
        this.registrationAt = registrationAt;
    }
}
