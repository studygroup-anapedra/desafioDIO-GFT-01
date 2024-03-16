package org.anapedra.schoolaertesaber.factories;

import org.anapedra.schoolaertesaber.dtos.EmailDTO;
import org.anapedra.schoolaertesaber.entities.Email;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.EmailType;

public class EmailFactory {

    public static Email createEmail() {
        return new Email(1L, "ananina@gmail.com",new Registration(),EmailType.WORK_EMAIL);
    }

    
    public static EmailDTO createEmailDTO() {
        Email email = createEmail();
        return new EmailDTO(email);
    }


}

