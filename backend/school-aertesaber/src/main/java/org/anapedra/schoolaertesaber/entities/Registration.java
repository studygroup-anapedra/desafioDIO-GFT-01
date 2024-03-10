package org.anapedra.schoolaertesaber.entities;

import jakarta.persistence.*;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "tb_registration")
public class Registration implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer registrationType;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String cpf;
    private String getRegistrationEmail;
    private LocalDate dateBirth;
    private String registrationPhone;
    private String profession;
    private String imgUrl;
    private Instant registrationAt;
    private Instant updateAt;

    public Registration() {
    }

    public Registration(Long id, RegistrationType registrationType, String firstName, String lastName, String cpf, String getRegistrationEmail,
                        LocalDate dateBirth, String registrationPhone, String profession, String imgUrl) {
        this.id = id;
        setRegistrationType(registrationType);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.getRegistrationEmail = getRegistrationEmail;
        this.dateBirth = dateBirth;
        this.registrationPhone = registrationPhone;
        this.profession = profession;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistrationType getRegistrationType() {
        return RegistrationType.valueOf(registrationType);
    }

    public void setRegistrationType(RegistrationType registrationType) {
        if (registrationType != null){
            this.registrationType = registrationType.getCode();
        }
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

    public String getGetRegistrationEmail() {
        return getRegistrationEmail;
    }

    public void setGetRegistrationEmail(String getRegistrationEmail) {
        this.getRegistrationEmail = getRegistrationEmail;
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

    public void setRegistrationAt(Instant registrationAt) {
        this.registrationAt = registrationAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCpf(), that.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpf());
    }
}
