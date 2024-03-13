package org.anapedra.schoolaertesaber.dtos;

import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;


import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrationUpdateDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;

    private RegistrationType registrationType;
    private String firstName;
    private String lastName;


    private String registrationEmail;
    private LocalDate dateBirth;
    private String registrationPhone;
    private String profession;
    private String imgUrl;

    private Instant updateAt;
    private Instant registrationAt;


    private List<PhoneDTO> phones =new ArrayList<>();

    public RegistrationUpdateDTO() {
    }

    public RegistrationUpdateDTO(Long id, RegistrationType registrationType, String firstName, String lastName, String registrationEmail,
                                 LocalDate dateBirth, String registrationPhone, String profession, String imgUrl, Instant updateAt) {
        this.id = id;
        this.registrationType = registrationType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationEmail = registrationEmail;
        this.dateBirth = dateBirth;
        this.registrationPhone = registrationPhone;
        this.profession = profession;
        this.imgUrl = imgUrl;
        this.updateAt = updateAt;
    }

    public RegistrationUpdateDTO(Registration entity) {
        id = entity.getId();
        registrationType =(registrationType == null) ? null: entity.getRegistrationType();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        registrationEmail = entity.getRegistrationEmail();
        dateBirth = entity.getDateBirth();
        registrationPhone = entity.getRegistrationPhone();
        profession = entity.getProfession();
        imgUrl = entity.getImgUrl();
        updateAt = Instant.now();
        registrationAt=entity.getRegistrationAt();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegistrationEmail() {
        return registrationEmail;
    }

    public void setRegistrationEmail(String registrationEmail) {
        this.registrationEmail = registrationEmail;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getRegistrationPhone() {
        return registrationPhone;
    }

    public void setRegistrationPhone(String registrationPhone) {
        this.registrationPhone = registrationPhone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public Instant getRegistrationAt() {
        return registrationAt;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }
}
