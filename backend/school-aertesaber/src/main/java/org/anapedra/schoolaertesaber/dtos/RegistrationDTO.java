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
import java.util.Objects;
import java.util.Set;

public class RegistrationDTO implements Serializable {
    private static final long serialVersionUID=1L;

    private Long id;

    private RegistrationType registrationType;
    private String firstName;
    private String lastName;

    @NotEmpty
    @CPF
    private String cpf;
    private String registrationEmail;
    private LocalDate dateBirth;
    private String registrationPhone;
    private String profession;
    private String imgUrl;
    private Instant registrationAt;
    private  Instant update;

    private List<PhoneDTO> phones =new ArrayList<>();


    public RegistrationDTO() {
    }

    public RegistrationDTO(Long id, RegistrationType registrationType, String firstName, String lastName, String cpf,
                           String registrationEmail, LocalDate dateBirth, String registrationPhone, String profession, String imgUrl) {
        this.id = id;
        this.registrationType = registrationType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.registrationEmail = registrationEmail;
        this.dateBirth = dateBirth;
        this.registrationPhone = registrationPhone;
        this.profession = profession;
        this.imgUrl = imgUrl;
    }

    public RegistrationDTO(Registration entity) {
        id = entity.getId();
        registrationType =(entity.getRegistrationType() == null) ? null: entity.getRegistrationType();        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        cpf = entity.getCpf();
        registrationEmail = entity.getRegistrationEmail();
        dateBirth = entity.getDateBirth();
        registrationPhone = entity.getRegistrationPhone();
        profession = entity.getProfession();
        imgUrl = entity.getImgUrl();
        registrationAt=Instant.now();
        update=entity.getRegistrationAt();
        entity.getPhones().forEach(phone -> this.phones.add(new PhoneDTO(phone)));


    }



    public RegistrationDTO(Registration entity, Set<Phone> phoness){
        this(entity);
        entity.getPhones().forEach(phone -> this.phones.add(new PhoneDTO(phone)));

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Instant getRegistrationAt() {
        return registrationAt;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public Instant getUpdate() {
        return update;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationDTO that = (RegistrationDTO) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCpf(), that.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpf());
    }
}
