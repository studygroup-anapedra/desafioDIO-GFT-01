package org.anapedra.schoolaertesaber.entities.enums;

public enum EmailType {



    /*
    Attention: When inserting another enumerator, sequence the integers
    in the proposed order to avoid a possible collapse of subsequent codes.
     */

    WORK_EMAIL(3),
    PERSONAL_EMAIL(3);


    private int code;

    private EmailType(int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
    public static EmailType valueOf(int code){
        for(EmailType value : EmailType.values() ) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code!");
    }
}
