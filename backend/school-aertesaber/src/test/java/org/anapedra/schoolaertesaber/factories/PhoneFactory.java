package org.anapedra.schoolaertesaber.factories;


import org.anapedra.schoolaertesaber.dtos.PhoneDTO;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.PhoneType;

public class PhoneFactory {



    public static Phone createPhone() {
        return new Phone(1L, "35264789", PhoneType.WORK_PHONE,new Registration());
    }


    public static PhoneDTO createPhoneDTO() {
        Phone phone = createPhone();
        return new PhoneDTO(phone);
    }



/*

public class EmailFactory {

    public static Email createEmail() {
        return new Email(1L, "ananina@gmail.com", EmailType.WORK_EMAIL,new Registration());
    }


    public static EmailDTO createEmailDTO() {
        Email email = createEmail();
        return new EmailDTO(email);
    }


 */


}
