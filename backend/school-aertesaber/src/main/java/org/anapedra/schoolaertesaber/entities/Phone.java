package org.anapedra.schoolaertesaber.entities;

import jakarta.persistence.*;
import org.anapedra.schoolaertesaber.entities.enums.PhoneType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_phone")
public class Phone implements Serializable {
    @Serial
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private Integer phoneType;
    @ManyToOne
    @JoinColumn(name = "registrationId")
    private Registration registration;



    public Phone() {
    }
    public Phone(Long id, String number, PhoneType phoneType,Registration registration) {
        this.id = id;
        this.number = number;

        setPhoneType(phoneType);

        this.registration=registration;
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

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public PhoneType getPhoneType() {
        return PhoneType.valueOf(phoneType);
    }

    public void setPhoneType(PhoneType phoneType) {
        if (phoneType != null){
            this.phoneType = phoneType.getCode();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(getId(), phone.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}