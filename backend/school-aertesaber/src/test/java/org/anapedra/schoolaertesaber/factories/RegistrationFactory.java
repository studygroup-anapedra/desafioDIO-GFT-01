package org.anapedra.schoolaertesaber.factories;

import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistrationFactory {



public static Registration createRegistration() {
    Registration registration = new Registration(1L, RegistrationType.EMPLOYEE_REGISTRATION,"Ana Lucia","Lopes de Santana","785.925.970-21","ana@gmail.com", LocalDate.parse("1980-10-10"),"230406755","Professora","");
    return registration;
}



public static RegistrationDTO createRegistrationDTO() {
    Registration createRegistration = createRegistration();
    return new RegistrationDTO(createRegistration);
}


}

