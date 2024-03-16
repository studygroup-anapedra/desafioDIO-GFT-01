package org.anapedra.schoolaertesaber.reposirories;

import org.anapedra.schoolaertesaber.entities.Phone;

import org.anapedra.schoolaertesaber.entities.enums.PhoneType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {
    @Query("SELECT DISTINCT obj FROM Phone obj WHERE " +
            "(LOWER(obj.registration.lastName) LIKE LOWER(CONCAT('%',:lastName,'%'))) AND "+
            "(LOWER(obj.registration.firstName) LIKE LOWER(CONCAT('%',:firstName,'%'))) AND "+
            "(LOWER(obj.registration.profession) LIKE LOWER(CONCAT('%',:profession,'%'))) AND "+
            "(obj.registration.dateBirth BETWEEN :minDateBirth AND :maxDateBirth)" )
    Page<Phone>findAllPhone(String lastName, String firstName, String profession, LocalDate minDateBirth,LocalDate maxDateBirth, Pageable pageable);


}
/*
     "(LOWER(obj.registration.registrationType) LIKE LOWER(CONCAT('%',:registrationType,'%'))) AND "+
        "obj.registration.registrationAt BETWEEN :minInstantRegistration AND :maxInstantRegistration

 */