package org.anapedra.schoolaertesaber.services;

import org.anapedra.schoolaertesaber.dtos.PhoneDTO;
import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.reposirories.PhoneRepository;
import org.anapedra.schoolaertesaber.reposirories.RegistrationRepository;
import org.anapedra.schoolaertesaber.services.exceptions.DataBaseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final RegistrationRepository repository;
    private final PhoneRepository phoneRepository;

    public RegistrationService(RegistrationRepository repository, PhoneRepository phoneRepository) {
        this.repository = repository;
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public RegistrationDTO insert(RegistrationDTO dto) {
        try {
            Registration entity = new Registration();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new RegistrationDTO(entity);

        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation: CPF já existe no sistema!");
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
}
