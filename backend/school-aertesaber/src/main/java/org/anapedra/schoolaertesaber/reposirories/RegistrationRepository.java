package org.anapedra.schoolaertesaber.reposirories;

import org.anapedra.schoolaertesaber.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    Optional<Registration> findByCpf(String cpf);
}
