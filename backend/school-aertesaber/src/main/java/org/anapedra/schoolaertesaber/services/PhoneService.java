package org.anapedra.schoolaertesaber.services;

import org.anapedra.schoolaertesaber.dtos.PhoneDTO;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.reposirories.PhoneRepository;;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneService {


    private final PhoneRepository repository;
    public PhoneService(PhoneRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public PhoneDTO insert(PhoneDTO dto) {

            Phone entity = new Phone();
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new PhoneDTO(entity);



    }

    private void copyDtoToEntity(PhoneDTO dto, Phone entity) {

        entity.setNumber(dto.getNumber());
        entity.setPhoneType(dto.getPhoneType());




    }


}
