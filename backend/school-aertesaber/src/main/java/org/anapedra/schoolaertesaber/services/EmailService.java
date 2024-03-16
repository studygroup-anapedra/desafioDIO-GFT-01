package org.anapedra.schoolaertesaber.services;

import jakarta.persistence.EntityNotFoundException;
import org.anapedra.schoolaertesaber.dtos.EmailDTO;
import org.anapedra.schoolaertesaber.dtos.EmailGetDTO;
import org.anapedra.schoolaertesaber.entities.Email;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.reposirories.EmailRepository;
import org.anapedra.schoolaertesaber.services.exceptions.DataBaseException;
import org.anapedra.schoolaertesaber.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class EmailService {


    private final EmailRepository repository;
    public EmailService(EmailRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public Page<EmailGetDTO> findAllPaged(String firstName, String lastName, String profession, String minDate, String maxDate, Pageable pageable){
        LocalDate today=LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min = minDate.isEmpty() ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max= maxDate.isEmpty() ? today : LocalDate.parse(maxDate);
        Page<Email> page=repository.findAllEmail(firstName,lastName,profession,min,max,pageable);
        return page.map(EmailGetDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<EmailGetDTO> findAllPaged(Pageable pageable) {
        Page<Email> list = repository.findAll(pageable);
        return list.map(EmailGetDTO::new);
    }



    @Transactional(readOnly = true)
    public EmailGetDTO findById(Long id) {
        Optional<Email> obj = repository.findById(id);
        Email entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new EmailGetDTO(entity);
    }



    @Transactional
    public EmailDTO insert(EmailDTO dto) {

        Email entity = new Email();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new EmailDTO(entity);

    }

    @Transactional
    public EmailDTO update(Long id, EmailDTO dto) {
        try {
            Email entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EmailDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }


    private void copyDtoToEntity(EmailDTO dto, Email entity) {

        entity.setEmail(dto.getEmail());
        entity.setEmailType(dto.getEmailType());

        Registration registration= new Registration();
        registration.setId(dto.getRegistrationId());
        entity.setRegistration(registration);


    }
}

