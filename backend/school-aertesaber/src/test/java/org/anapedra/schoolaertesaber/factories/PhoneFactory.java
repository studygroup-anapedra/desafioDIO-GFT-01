package org.anapedra.schoolaertesaber.factories;


import org.anapedra.schoolaertesaber.dtos.PhoneDTO;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.enums.PhoneType;

public class PhoneFactory {



    public static Phone createPhone() {
        return new Phone(1L, "35264789", PhoneType.WORK_PHONE);
    }



    public static PhoneDTO createPhoneDTO() {
        Phone phone = createPhone();
        return new PhoneDTO(phone);
    }






}
