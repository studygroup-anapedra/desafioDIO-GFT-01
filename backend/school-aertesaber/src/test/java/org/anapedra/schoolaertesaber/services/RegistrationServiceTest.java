package org.anapedra.schoolaertesaber.services;


import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.factories.RegistrationFactory;
import org.anapedra.schoolaertesaber.reposirories.RegistrationRepository;
import org.anapedra.schoolaertesaber.services.exceptions.DataBaseException;
import org.anapedra.schoolaertesaber.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService service;
    @Mock
    private RegistrationRepository repository;





    @Test
    public void  insertShouldSaveObjectWhenCorrectDate(){
        when(repository.save(ArgumentMatchers.any())).thenReturn(RegistrationFactory.createRegistration());

        service.insert(RegistrationFactory.createRegistrationDTO());

        Optional<Registration> result = repository.findByCpf(RegistrationFactory.createRegistrationDTO().getCpf());
        Assertions.assertNotNull(result);
        assertEquals( "Ana Lucia", RegistrationFactory.createRegistrationDTO().getFirstName());
        assertEquals("785.925.970-21",RegistrationFactory.createRegistrationDTO().getCpf());
        assertEquals( "ana@gmail.com", RegistrationFactory.createRegistrationDTO().getRegistrationEmail());

        Mockito.verify(repository, times(1)).findByCpf(RegistrationFactory.createRegistrationDTO().getCpf());


    }
    @Test
    public void  insertShouldThrowsDataBaseExceptionWhenExistingCpf(){

        Registration registration =new Registration();
        RegistrationDTO registrationDTO = new RegistrationDTO();

        doThrow(DataBaseException.class).when(repository).save(registration);
        Assertions.assertThrows(DataBaseException.class,() -> {
            service.insert(registrationDTO);
        });

        Optional<Registration> result = repository.findByCpf(RegistrationFactory.createRegistrationDTO().getCpf());
        Assertions.assertEquals(result,Optional.empty());

        Mockito.verify(repository, times(0)).findByCpf(registrationDTO.getCpf());





    }


    @Test
    public void findByCpfShouldReturnRegistrationWhenExistingCpf() {
        Registration registrationPeople =new Registration();
        RegistrationDTO registrationDTO = RegistrationFactory.createRegistrationDTO();
        Mockito.when(repository.findById(registrationDTO.getId())).thenReturn(Optional.of(registrationPeople));

        Optional<RegistrationDTO> result = Optional.ofNullable(service.findById(registrationDTO.getId()));

        Assertions.assertTrue(result.isPresent());
        Assertions.assertNotNull(result);
        assertEquals( "Ana Lucia", registrationDTO.getFirstName());
        assertEquals("785.925.970-21",registrationDTO.getCpf());
        assertEquals( "ana@gmail.com", registrationDTO.getRegistrationEmail());

        Mockito.verify(repository, times(1)).findById(registrationDTO.getId());
    }


    @Test
    public void findByCpfThrowResourceNotFindExceptionWhenDoesNotExistingCpf() throws Exception{


        RegistrationDTO registrationDTO = RegistrationFactory.createRegistrationDTO();
        registrationDTO.setCpf("623.662.420-85");
        Mockito.when(repository.getById(registrationDTO.getId())).thenThrow(ResourceNotFoundException.class);

        Assertions.assertThrows(ResourceNotFoundException.class,() -> {
            service.findByCpf(registrationDTO.getCpf());
        });

        Mockito.verify(repository, times(1)).findByCpf(registrationDTO.getCpf());//@@

    }


    @Test
    public void findBIdThrowResourceNotFindExceptionWhenDoesNotExistingId() throws Exception{


        RegistrationDTO registrationDTO = RegistrationFactory.createRegistrationDTO();
        registrationDTO.setId(100L);

        Assertions.assertThrows(ResourceNotFoundException.class,() -> {
            service.findById(registrationDTO.getId());
        });

        Mockito.verify(repository, times(1)).findById(registrationDTO.getId());//@@

    }





}


