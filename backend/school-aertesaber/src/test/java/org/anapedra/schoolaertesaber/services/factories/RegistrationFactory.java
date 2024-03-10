package org.anapedra.schoolaertesaber.services.factories;

import java.time.LocalTime;

public class RegistrationFactory {



public static Registration createRegistration() {
    Registration registration = new Registration(1L,RegistrationType.EMPLOYEE_REGISTRATION,"Ana Lucia","Lopes de Santana","01589021576","ana@gmail.com", LocalTime.parse("1980-10-10"),"230406755","Professora");
    return registration;
}



public static RegistrationDTO createRegistrationDTO() {
    Registration createRegistration = createRegistration();
    return new RegistrationDTO(createRegistration);
}


}

