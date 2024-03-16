package org.anapedra.schoolaertesaber;

import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.PhoneType;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;
import org.anapedra.schoolaertesaber.reposirories.PhoneRepository;
import org.anapedra.schoolaertesaber.reposirories.RegistrationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SchoolAertesaberApplication implements CommandLineRunner {
    private final RegistrationRepository registrationRepository;
    private final PhoneRepository phoneRepository;

    public SchoolAertesaberApplication(RegistrationRepository registrationRepository, PhoneRepository phoneRepository) {
        this.registrationRepository = registrationRepository;
        this.phoneRepository = phoneRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolAertesaberApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Registration> registrations = new ArrayList<>();
        List<Phone>phones = new ArrayList<>();





        Registration r1 = new Registration(1L, RegistrationType.EMPLOYEE_REGISTRATION,"Carla","Madonça","01589021576","carla@gmail.com", LocalDate.parse("1980-10-10"),"61987564","profewssora","");
      //  r1.getPhones().addAll(Arrays.asList(p2,p3));

        Registration r2 = new Registration(2L, RegistrationType.EMPLOYEE_REGISTRATION,"Maria","Madonça","929.174.970-25","carla@gmail.com", LocalDate.parse("1980-10-10"),"61987564","profewssora","");
       // r2.getPhones().addAll(Arrays.asList(p1,p4));

        registrations.addAll(Arrays.asList(r1,r2));
        registrationRepository.saveAll(registrations);

        Phone p1 = new Phone(1L,"23564789", PhoneType.HOME_PHONE,r2);
        Phone p2 = new Phone(2L,"23564789", PhoneType.WORK_PHONE,r2);
        Phone p3 = new Phone(3L,"23564789", PhoneType.HOME_PHONE,r2);
        Phone p4 = new Phone(4L,"23564789", PhoneType.MOBILE_PHONE,r1);

        phones.addAll(Arrays.asList(p1,p2,p3,p4));
        phoneRepository.saveAll(phones);










    }
}
