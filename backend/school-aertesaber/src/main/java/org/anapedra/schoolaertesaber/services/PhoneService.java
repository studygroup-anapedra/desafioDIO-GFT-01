package org.anapedra.schoolaertesaber.services;

import jakarta.persistence.EntityNotFoundException;
import org.anapedra.schoolaertesaber.dtos.PhoneDTO;
import org.anapedra.schoolaertesaber.dtos.PhoneGetDTO;
import org.anapedra.schoolaertesaber.entities.Phone;

import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.reposirories.PhoneRepository;;
import org.anapedra.schoolaertesaber.services.exceptions.DataBaseException;
import org.anapedra.schoolaertesaber.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PhoneService {


    private final PhoneRepository repository;
    public PhoneService(PhoneRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public Page<PhoneGetDTO> findAllPaged(Pageable pageable) {
        Page<Phone> list = repository.findAll(pageable);
        return list.map(PhoneGetDTO::new);
    }

    @Transactional(readOnly = true)
    public PhoneGetDTO findById(Long id) {
        Optional<Phone> obj = repository.findById(id);
        Phone entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PhoneGetDTO(entity);
    }



    @Transactional
    public PhoneDTO insert(PhoneDTO dto) {

        Phone entity = new Phone();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PhoneDTO(entity);

    }




    @Transactional
    public PhoneDTO update(Long id, PhoneDTO dto) {
        try {
            Phone entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new PhoneDTO(entity);
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




    private void copyDtoToEntity(PhoneDTO dto, Phone entity) {

        entity.setNumber(dto.getNumber());
        entity.setPhoneType(dto.getPhoneType());

        Registration registration= new Registration();
        registration.setId(dto.getRegistrationId());
        entity.setRegistration(registration);




    }


}
