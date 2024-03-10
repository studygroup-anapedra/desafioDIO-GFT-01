package org.anapedra.schoolaertesaber.services;

import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.factories.RegistrationFactory;
import org.anapedra.schoolaertesaber.reposirories.RegistrationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class RegistrationServiceTest {
    @InjectMocks
    private RegistrationService service;
    @Mock
    private RegistrationRepository repository;




    private long existingId;
    private String existingCpf;
    private long nonExistingId;
    private  String nonExistingCpf;


    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        existingCpf = "01589021576";
        nonExistingCpf = " 11255555555";
        nonExistingId = 2L;

    }


    @Test
    public void  insertShouldSaveObjectWhenCorrectDate(){
        Registration registration =new Registration();
        when(repository.save(ArgumentMatchers.any())).thenReturn(registration);
        RegistrationDTO insertDTO=new RegistrationDTO(RegistrationFactory.createRegistration());

           service.insert(RegistrationFactory.createRegistrationDTO());

        Optional<Registration> result = repository.findByCpf(insertDTO.getCpf());
        Assertions.assertNotNull(result);
        assertEquals( "Ana Lucia", insertDTO.getFirstName());
        assertEquals("785.925.970-21",insertDTO.getCpf());
        assertEquals( "ana@gmail.com", insertDTO.getGetRegistrationEmail());

        Mockito.verify(repository, times(1)).findByCpf(insertDTO.getCpf());


    }


    @Test
    public void  insertShouldThrowsDatabaseExceptionWhenExistCpf() throws Exception{
        Registration registration =new Registration();
        when(repository.save(ArgumentMatchers.any())).thenReturn(registration);
        RegistrationDTO insertDTO=new RegistrationDTO(RegistrationFactory.createRegistration());
        insertDTO.setCpf(existingCpf);


        Mockito.doThrow(DataBaseException.class).when(repository).save(registration);
        Assertions.assertThrows(DataBaseException.class,() -> {
            service.insert(RegistrationFactory.createRegistrationDTO());
        });

        Mockito.verify(repository, times(1)).findByCpf(insertDTO.getCpf());

    }


}

