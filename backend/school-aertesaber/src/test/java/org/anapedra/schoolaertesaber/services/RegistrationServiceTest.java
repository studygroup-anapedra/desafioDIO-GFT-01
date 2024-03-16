package org.anapedra.schoolaertesaber.services;

import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.dtos.RegistrationGetIdDTO;
import org.anapedra.schoolaertesaber.dtos.RegistrationMinDTO;
import org.anapedra.schoolaertesaber.dtos.RegistrationUpdateDTO;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.RegistrationType;
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

        RegistrationDTO registrationDTO = new RegistrationDTO();

        doThrow(DataBaseException.class).when(repository).save(new Registration());
        Assertions.assertThrows(DataBaseException.class,() -> {
            service.insert(registrationDTO);
        });

        Optional<Registration> result = repository.findByCpf(RegistrationFactory.createRegistrationDTO().getCpf());
        Assertions.assertEquals(result,Optional.empty());

        Mockito.verify(repository, times(0)).findByCpf(registrationDTO.getCpf());


    }

    @Test
    public void findByCpfShouldReturnRegistrationWhenExistingCpf() {
         String existCpf = "01589021576";

        RegistrationDTO dto = RegistrationFactory.createRegistrationDTO();
        Mockito.when(repository.findByCpf(existCpf)).thenReturn(Optional.of(RegistrationFactory.createRegistration()));

        Optional<RegistrationDTO> result = Optional.ofNullable(service.findByCpf(existCpf));

        Assertions.assertTrue(result.isPresent());
        Assertions.assertNotNull(result);
        assertEquals( "Ana Lucia", dto.getFirstName());
        assertEquals("785.925.970-21",dto.getCpf());
        assertEquals( "ana@gmail.com", dto.getRegistrationEmail());

        Mockito.verify(repository, times(1)).findByCpf(existCpf);
    }


    @Test
    public void findByIdShouldReturnRegistrationWhenExistingId() {
        long existId = 1L;


        RegistrationDTO dto = RegistrationFactory.createRegistrationDTO();
        Mockito.when(repository.findById(existId)).thenReturn(Optional.of(RegistrationFactory.createRegistration()));

        Optional<RegistrationDTO> result = Optional.ofNullable(service.findById(existId));

        Assertions.assertTrue(result.isPresent());
        Assertions.assertNotNull(result);
        assertEquals( "Ana Lucia", dto.getFirstName());
        assertEquals("785.925.970-21",dto.getCpf());
        assertEquals( "ana@gmail.com", dto.getRegistrationEmail());

        Mockito.verify(repository, times(1)).findById(existId);
    }


    @Test
    public void findByCpfThrowResourceNotFindExceptionWhenDoesNotExistingCpf() throws Exception{
         String nonExistCpf = "731.160.100-21";
        Mockito.when(repository.findByCpf(nonExistCpf)).thenThrow(ResourceNotFoundException.class);

        Assertions.assertThrows(ResourceNotFoundException.class,() -> {
            service.findByCpf(nonExistCpf);
        });

        Mockito.verify(repository, times(1)).findByCpf(nonExistCpf);

    }


    @Test
    public void findBIdThrowResourceNotFindExceptionWhenDoesNotExistingId() throws Exception{
        long  nonExistId = 2000000L;
        Mockito.doThrow(ResourceNotFoundException.class).when(repository).findById(nonExistId);

        Assertions.assertThrows(ResourceNotFoundException.class,() -> {
            service.findById(nonExistId);
        });

        Mockito.verify(repository, times(1)).findById(nonExistId);

    }

    @Test
    public void findAllShouldReturnPaged() {
        String firstName = "";
        String lastName = "";
        String profession = "";
        LocalDate min = LocalDate.parse("2012-10-10");
        LocalDate max = LocalDate.parse("2020-10-10");
        Pageable pageable = PageRequest.of(0, 12);
         PageImpl<Registration> page= new PageImpl<>(List.of(RegistrationFactory.createRegistration()));

        Mockito.when(repository.findAllRegistration(any(), any(),any(),any(),any(),(Pageable)ArgumentMatchers.any())).thenReturn(page);

        Page<RegistrationMinDTO> result = service.findAllPaged(firstName,lastName, profession, min.toString(),  max.toString(), pageable);

        Assertions.assertNotNull(result);
        assertEquals(result.getSize(), 1);

    }

    @Test
    public void findAllPagedShouldReturnPage() {

        PageImpl<Registration> page = new PageImpl<>(List.of(RegistrationFactory.createRegistration()));
        Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 12);

        Page<RegistrationGetIdDTO> result = service.findAllPaged(pageable);

        Assertions.assertNotNull(result);

        Mockito.verify(repository, times(1)).findAll(pageable);
    }

    @Test
    public void deleteShouldThrowResourceNotFindExceptionWhenDoesNotExistingId() {

        long  nonExistId = 2000000L;
        Mockito.doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistId);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistId);
        });

        Mockito.verify(repository, times(0)).deleteById(nonExistId);
    }

    @Test
    public void deleteShouldNoThingWhenDoesExistingId() {
        Long existId = 1L;
        Mockito.when(repository.existsById(existId)).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(existId);


        Assertions.assertDoesNotThrow(() -> {
            service.delete(existId);
        });

        Mockito.verify(repository, times(1)).deleteById(existId);

    }
    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDoesNotExistingId() {
        Long dependentId = 1L;
        Mockito.when(repository.existsById(dependentId)).thenReturn(true);
        Mockito.doThrow(DataBaseException.class).when(repository).deleteById(dependentId);



        Assertions.assertThrows(DataBaseException.class, () -> {
            service.delete(dependentId);
        });

        Mockito.verify(repository, times(1)).deleteById(dependentId);
    }


    @Test
    public void updateShouldReturnRegistrationDTOWhenIdExists() {
        Long existingId = 1L;
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(new Registration());

        when(repository.save(ArgumentMatchers.any())).thenReturn(RegistrationFactory.createRegistration());

        service.update(existingId,RegistrationFactory.createRegistrationUpdateDTO());

        Optional<Registration> result = repository.findById(RegistrationFactory.updateRegistration().getId());

        Assertions.assertNotNull(result);
        assertEquals( "Ana Lucia", RegistrationFactory.createRegistrationDTO().getFirstName());
        assertEquals("785.925.970-21",RegistrationFactory.createRegistrationDTO().getCpf());
        assertEquals( "ana@gmail.com", RegistrationFactory.createRegistrationDTO().getRegistrationEmail());
        assertEquals( RegistrationType.EMPLOYEE_REGISTRATION, RegistrationFactory.createRegistrationDTO().getRegistrationType());



        Mockito.verify(repository, times(1)).findById(RegistrationFactory.createRegistrationUpdateDTO().getId());

    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {
        long  nonExistingId = 2000000L;
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        RegistrationUpdateDTO dto= RegistrationFactory.createRegistrationUpdateDTO();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, dto);
        });
    }









}
