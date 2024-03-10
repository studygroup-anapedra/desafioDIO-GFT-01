package org.anapedra.schoolaertesaber.services;

import org.anapedra.schoolaertesaber.factories.RegistrationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        RegistrationDTO insertDTO= RegistrationFactory.createRegistrationDTO();
        when(repository.save(ArgumentMatchers.any())).thenReturn(registration);

        Optional<Registration> result = repository.findByCpf(insertDTO.getCpf());
        Assertions.assertNotNull(result);
        assertEquals( "Ana", insertDTO.getFirstName());
        assertEquals("785.925.970-21",insertDTO.getCpf());
        assertEquals( "anasantana@gmail.com", insertDTO.getEmail());

        Mockito.verify(repository, times(1)).findByCpf(insertDTO.getCpf());


    }

    @Test
    public void  insertShouldThrowsDatabaseExceptionWhenExistCpf() throws Exception{

        Registration registration =new Registration();
        RegistrationDTO registrationDTO = RegistrationFactory.createRegistrationDTO();
        registrationDTO.setCpf(existingId);


        Mockito.doThrow(DatabaseException.class).when(repository).save(registrationPeople);
        Assertions.assertThrows(DatabaseException.class,() -> {
            service.insert(registrationDTO);
        });

        Mockito.verify(repository, times(0)).findByCpf(registrationDTO.getCpf());

    }


}

