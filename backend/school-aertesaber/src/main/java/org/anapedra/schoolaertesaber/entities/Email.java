package org.anapedra.schoolaertesaber.entities;

import jakarta.persistence.*;
import org.anapedra.schoolaertesaber.entities.enums.EmailType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_email")
public class Email implements Serializable {


    @Serial
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "registration_Id")
    private Registration registration;
    private String email;
    private Integer emailType;

    public Email() {
    }

    public Email(Long id, String email, Registration registration, EmailType emailType) {
        this.id = id;
        this.registration = registration;
        this.email = email;
        setEmailType(emailType);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public EmailType getEmailType() {
        return EmailType.valueOf(emailType);
    }

    public void setEmailType(EmailType emailType) {
        if (emailType != null){
            this.emailType= emailType.getCode();
        }
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       Email email = (Email) o;
        return Objects.equals(getId(), email.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }{
}
}
