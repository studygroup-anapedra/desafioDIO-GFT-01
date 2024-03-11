package org.anapedra.schoolaertesaber.services;


import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.factories.PhoneFactory;
import org.anapedra.schoolaertesaber.factories.RegistrationFactory;
import org.anapedra.schoolaertesaber.reposirories.PhoneRepository;
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

        Optional<Phone> result = repository.findById(RegistrationFactory.createRegistration().getId());
        Assertions.assertNotNull(result);
        assertEquals( "35264789", PhoneFactory.createPhoneDTO().getNumber());
        assertEquals(1l,PhoneFactory.createPhoneDTO().getId());


        Mockito.verify(repository, times(1)).findById(RegistrationFactory.createRegistrationDTO().getId());


    }



}
