package org.anapedra.schoolaertesaber.dtos;

import jakarta.validation.constraints.NotEmpty;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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





    public RegistrationMinDTO(Registration entity) {
        id = entity.getId();
        registrationType =(entity.getRegistrationType() == null) ? null: entity.getRegistrationType();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        cpf = entity.getCpf();
        registrationEmail = entity.getRegistrationEmail();
        registrationPhone = entity.getRegistrationPhone();
        profession = entity.getProfession();
        imgUrl = entity.getImgUrl();





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



}
