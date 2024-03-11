package org.anapedra.schoolaertesaber.factories;


import org.anapedra.schoolaertesaber.entities.Registration;

public class PhoneFactory {



    public static Phone createPhone() {
        Registration registration=RegistrationFactory.createRegistration();
        Long registrationID =registration.getId();
        Phone phone = new Phone(1L, "35264789",PhoneType.WORK,registrationID);
        return phone;
    }



    public static PhoneDTO createPhoneDTO() {
        Phone phone = createPhone();
        return new PhoneDTO(phone);
    }






}
