package org.anapedra.schoolaertesaber.services;


import jakarta.persistence.EntityNotFoundException;
import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.dtos.RegistrationMinDTO;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.factories.RegistrationFactory;
import org.anapedra.schoolaertesaber.reposirories.RegistrationRepository;
import org.anapedra.schoolaertesaber.services.exceptions.DataBaseException;
import org.anapedra.schoolaertesaber.services.exceptions.ResourceNotFoundException;
import org.h2.table.DataChangeDeltaTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService service;
    @Mock
    private RegistrationRepository repository;

    private long existingId, nonExistingId, dependentId;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 2000000L;
        dependentId = 2L;

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(new Registration()));
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.getReferenceById(existingId)).thenReturn(new Registration());
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.when(repository.existsById(dependentId)).thenReturn(true);
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);

        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.doThrow(DataBaseException.class).when(repository).deleteById(dependentId);



    }


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



    @Test
    public void findAllShouldReturnPaged() {
        Registration registration=new Registration();
        String firstName = "";
        String lastName = "";
        String profession = "";
        LocalDate min = LocalDate.parse("2012-10-10");
        LocalDate max = LocalDate.parse("2020-10-10");
        Pageable pageable = PageRequest.of(0, 12);
         PageImpl<Registration> page= new PageImpl<>(List.of(registration));

        Mockito.when(repository.findAllRegistration(any(), any(),any(),any(),any(),(Pageable)ArgumentMatchers.any())).thenReturn(page);

        Page<RegistrationMinDTO> result = service.findAllPaged(firstName,lastName, profession, min.toString(),  max.toString(), pageable);

        Assertions.assertNotNull(result);
        assertEquals(result.getSize(), 1);

    }



    @Test
    public void findAllPagedShouldReturnPage() {
        Registration registration =new Registration();
        PageImpl<Registration> page = new PageImpl<>(List.of(registration));
        Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 12);

        Page<RegistrationMinDTO> result = service.findAllPaged(pageable);

        Assertions.assertNotNull(result);

        Mockito.verify(repository, times(1)).findAll(pageable);
    }






    @Test
    public void deleteShouldThrowResourceNotFindExceptionWhenDoesNotExistingId() {


        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });

        Mockito.verify(repository, times(0)).deleteById(nonExistingId);
    }




    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDoesNotExistingId() {
        Mockito.doNothing().when(repository).deleteById(dependentId);

        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });

        Mockito.verify(repository, times(0)).deleteById(nonExistingId);
    }


    @Test
    public void updateShouldThrowResourceNotFindExceptionWhenIdDoesNotExit() throws Exception{
        Registration registrationPeople=new Registration();
        RegistrationDTO registrationPeopleDTO= RegistrationFactory.createRegistrationDTO();

        Mockito.doThrow(ResourceNotFoundException.class).when(repository).save(registrationPeople);
        Assertions.assertThrows(ResourceNotFoundException.class,() -> {
            service.update(nonExistingId,registrationPeopleDTO);
        });

        Mockito.verify(repository, times(1)).findById(registrationPeopleDTO.getId());

    }




}

