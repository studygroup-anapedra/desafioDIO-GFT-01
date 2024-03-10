package org.anapedra.schoolaertesaber;

import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;
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

    public SchoolAertesaberApplication(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolAertesaberApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Registration> registrations = new ArrayList<>();

        Registration r1 = new Registration(1L, RegistrationType.EMPLOYEE_REGISTRATION,"Carla","Madonça","01589021576","carla@gmail.com", LocalDate.parse("1980-10-10"),"61987564","profewssora","");

        Registration r2 = new Registration(2L, RegistrationType.EMPLOYEE_REGISTRATION,"Maria","Madonça","929.174.970-25","carla@gmail.com", LocalDate.parse("1980-10-10"),"61987564","profewssora","");
        registrations.addAll(Arrays.asList(r1,r2));
        registrationRepository.saveAll(registrations);



    }
}
