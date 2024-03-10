package org.anapedra.schoolaertesaber.services;

import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {


    @Transactional
    public RegistrationDTO insert(RegistrationDTO dto) {
        try {
            Registration entity = new Registration();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new RegistrationDTO(entity);

        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation!");
        }

    }





    private void copyDtoToEntity(RegistrationDTO dto, Registration entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setRegistrationType(dto.getRegistrationType());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCpf(dto.getCpf());
        entity.setGetRegistrationEmail(dto.getGetRegistrationEmail());
        entity.setDateBirth(dto.getDateBirth());
        entity.setRegistrationPhone(dto.getRegistrationPhone());
        entity.setProfession(dto.getProfession());
        entity.setImgUrl(dto.getImgUrl());



    }
}
