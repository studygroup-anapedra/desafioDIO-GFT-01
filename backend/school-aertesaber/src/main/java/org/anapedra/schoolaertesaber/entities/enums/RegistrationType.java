package org.anapedra.schoolaertesaber.entities.enums;

public enum RegistrationType {

    /*
    Attention: When inserting another enumerator, sequence the integers
    in the proposed order to avoid a possible collapse of subsequent codes.
     */


    EMPLOYEE_REGISTRATION(1),
    STUDENT_REGISTRATION(2);


    private int code;

    private RegistrationType(int code){
        this.code=code;
    }
    public int getCode(){
        return code;
    }
    public static RegistrationType valueOf(int code){
        for(RegistrationType value : RegistrationType.values() ) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code!");
    }

}
