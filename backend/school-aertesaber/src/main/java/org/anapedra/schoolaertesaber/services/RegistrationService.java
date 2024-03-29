package org.anapedra.schoolaertesaber.services;

import jakarta.persistence.EntityNotFoundException;
import org.anapedra.schoolaertesaber.dtos.*;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.reposirories.PhoneRepository;
import org.anapedra.schoolaertesaber.reposirories.RegistrationRepository;
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
public class RegistrationService {

    private final RegistrationRepository repository;
    private final PhoneRepository phoneRepository;

    public RegistrationService(RegistrationRepository repository, PhoneRepository phoneRepository) {
        this.repository = repository;
        this.phoneRepository = phoneRepository;
    }



    @Transactional(readOnly = true)
    public Page<RegistrationMinDTO> findAllPaged(String firstName, String lastName, String profession, String minDate, String maxDate, Pageable pageable){
        LocalDate today=LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min = minDate.isEmpty() ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max= maxDate.isEmpty() ? today : LocalDate.parse(maxDate);
        Page<Registration> page=repository.findAllRegistration(firstName,lastName,profession,min,max,pageable);
        return page.map(RegistrationMinDTO::new);
    }


    @Transactional(readOnly = true)
    public Page<RegistrationGetIdDTO> findAllPaged(Pageable pageable) {
        Page<Registration> list = repository.findAll(pageable);
        return list.map(RegistrationGetIdDTO::new);
    }

    @Transactional(readOnly = true)
    public RegistrationDTO findByCpf(String cpf) {
        Optional<Registration> obj = repository.findByCpf(cpf);
        Registration entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new RegistrationDTO(entity);


    }

    @Transactional(readOnly = true)
    public RegistrationDTO findById(long id) {
        Optional<Registration> obj = repository.findById(id);
        Registration entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new RegistrationDTO(entity);
    }



    @Transactional
    public RegistrationDTO insert(RegistrationDTO dto) {
        if (!repository.existsByCpf(dto.getCpf())) {

            Registration entity = new Registration();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new RegistrationDTO(entity);

        }

        throw new DataBaseException("Integrity violation: CPF já existe no sistema!");


    }

    @Transactional
    public RegistrationUpdateDTO update(Long id, RegistrationUpdateDTO dto) {
        try {
            Registration entity = repository.getReferenceById(id);
            copyDtoToEntityUpdate(dto, entity);
            entity = repository.save(entity);
            return new RegistrationUpdateDTO(entity);
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

    private void copyDtoToEntity(RegistrationDTO dto, Registration entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setRegistrationType(dto.getRegistrationType());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCpf(dto.getCpf());
        entity.setRegistrationEmail(dto.getRegistrationEmail());
        entity.setDateBirth(dto.getDateBirth());
        entity.setRegistrationPhone(dto.getRegistrationPhone());
        entity.setProfession(dto.getProfession());
        entity.setImgUrl(dto.getImgUrl());


        entity.getPhones().clear();
        for (PhoneDTO phoneDto : dto.getPhones()) {
            Phone phone = phoneRepository.getOne(phoneDto.getId());
            entity.getPhones().add(phone);
        }

    }

    private void copyDtoToEntityUpdate(RegistrationUpdateDTO dto, Registration entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setRegistrationType(dto.getRegistrationType());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setRegistrationEmail(dto.getRegistrationEmail());
        entity.setDateBirth(dto.getDateBirth());
        entity.setRegistrationPhone(dto.getRegistrationPhone());
        entity.setProfession(dto.getProfession());
        entity.setImgUrl(dto.getImgUrl());

       /*
        entity.getPhones().clear();
        for (PhoneDTO phoneDto : dto.getPhones()) {
            Phone phone = phoneRepository.getOne(phoneDto.getId());
            entity.getPhones().add(phone);
        }

        */




    }
}




