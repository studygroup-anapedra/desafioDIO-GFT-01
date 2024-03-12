package org.anapedra.schoolaertesaber.reposirories;

import org.anapedra.schoolaertesaber.entities.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    Optional<Registration> findByCpf(String cpf);

    @Query("SELECT obj FROM Registration obj WHERE UPPER" +
            "(obj.firstName) LIKE UPPER(CONCAT('%', :firstName, '%')) AND " +
            "(obj.lastName) LIKE UPPER(CONCAT('%', :lastName, '%')) AND " +
            "(obj.profession) LIKE UPPER(CONCAT('%', :profession, '%')) AND " +
            "(obj.dateBirth BETWEEN :min AND :max) ")

            Page<Registration> findAllRegistration(String firstName, String lastName, String profession, LocalDate min,LocalDate max,Pageable pageable);



}
