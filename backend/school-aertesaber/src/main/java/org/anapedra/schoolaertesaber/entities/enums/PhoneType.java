package org.anapedra.schoolaertesaber.entities.enums;

public enum PhoneType {


    /*
    Attention: When inserting another enumerator, sequence the integers
    in the proposed order to avoid a possible collapse of subsequent codes.
     */

    WHATSAPP(1),
    WORK_PHONE(2),
    HOME_PHONE(3),
    PERSONAL_PHONE(4);




    private int code;

    private PhoneType(int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
    public static PhoneType valueOf(int code){
        for(PhoneType value : PhoneType.values() ) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code!");
    }

}
