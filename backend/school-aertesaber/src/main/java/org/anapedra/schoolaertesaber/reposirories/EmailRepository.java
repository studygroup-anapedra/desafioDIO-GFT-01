package org.anapedra.schoolaertesaber.reposirories;

import org.anapedra.schoolaertesaber.entities.Email;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {

    @Query("SELECT DISTINCT obj FROM Email obj WHERE " +
            "(LOWER(obj.registration.lastName) LIKE LOWER(CONCAT('%',:lastName,'%'))) AND "+
            "(LOWER(obj.registration.firstName) LIKE LOWER(CONCAT('%',:firstName,'%'))) AND "+
            "(LOWER(obj.registration.profession) LIKE LOWER(CONCAT('%',:profession,'%'))) AND "+
            "(obj.registration.dateBirth BETWEEN :minDateBirth AND :maxDateBirth)" )
    Page<Email> findAllEmail(String lastName, String firstName, String profession, LocalDate minDateBirth, LocalDate maxDateBirth, Pageable pageable);


}
