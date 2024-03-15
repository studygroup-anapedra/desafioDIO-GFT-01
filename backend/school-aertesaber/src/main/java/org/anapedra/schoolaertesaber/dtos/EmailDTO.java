package org.anapedra.schoolaertesaber.dtos;

import org.anapedra.schoolaertesaber.entities.Email;
import org.anapedra.schoolaertesaber.entities.enums.EmailType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class EmailDTO implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    private Long id;

    private String email;
    private EmailType emailType;

    private  Long registrationId;

    public EmailDTO() {
    }

    public EmailDTO(Long id, String email, EmailType emailType, Long registrationId) {
        this.id = id;
        this.email = email;
        this.emailType = emailType;
        this.registrationId = registrationId;
    }

    public EmailDTO(Email entity) {
        id = entity.getId();
        email = entity.getEmail();
        emailType = (entity.getEmailType() == null) ? null: entity.getEmailType();
        registrationId=entity.getRegistration().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDTO emailDTO = (EmailDTO) o;
        return Objects.equals(getId(), emailDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }{
}
}
