package org.anapedra.schoolaertesaber.entities.enums;

public enum PhoneType {
    /*
    Attention: When inserting another enumerator, sequence the integers
    in the proposed order to avoid a possible collapse of subsequent codes.
     */

    HOME_PHONE(1),
    WORK_PHONE(2),
    MOBILE_PHONE_WHATSAPP(3),
    WHATSAPP(4),
    MOBILE_PHONE(5);


    private int code;

    private PhoneType(int code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public static PhoneType valueOf(int code){
        for (PhoneType volue: PhoneType.values()){
            if (volue.getCode()==code){
                return volue;
            }
        }
        throw new IllegalArgumentException("Invalid code!");
    }
}
