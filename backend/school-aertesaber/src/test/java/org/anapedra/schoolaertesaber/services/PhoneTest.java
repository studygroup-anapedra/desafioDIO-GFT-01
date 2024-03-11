package org.anapedra.schoolaertesaber.services;

import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.factories.PhoneFactory;
import org.anapedra.schoolaertesaber.factories.RegistrationFactory;
import org.anapedra.schoolaertesaber.reposirories.PhoneRepository;
import org.anapedra.schoolaertesaber.reposirories.RegistrationRepository;
import org.anapedra.schoolaertesaber.services.exceptions.DataBaseException;
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
import static org.mockito.Mockito.times;
@ExtendWith(SpringExtension.class)
public class PhoneTest {


    @InjectMocks
    private PhoneService service;
    @Mock
    private PhoneRepository repository;





    @Test
    public void  insertShouldSaveObjectWhenCorrectDate(){
        when(repository.save(ArgumentMatchers.any())).thenReturn(PhoneFactory.createPhone());

        service.insert(PhoneFactory.createPhoneDTO());

        Optional<Registration> result = repository.findById(RegistrationFactory.createRegistrationDTO().getById);
        Assertions.assertNotNull(result);
        assertEquals( "35264789", PhoneFactory.createPhoneDTO().getNumber());
        assertEquals("WORK",RegistrationFactory.createRegistrationDTO().getCpf());


        Mockito.verify(repository, times(1)).findById(RegistrationFactory.createRegistrationDTO().getId());


    }



}
