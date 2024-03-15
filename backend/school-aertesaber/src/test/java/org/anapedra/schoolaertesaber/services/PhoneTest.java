package org.anapedra.schoolaertesaber.services;


import jakarta.persistence.EntityNotFoundException;
import org.anapedra.schoolaertesaber.dtos.PhoneDTO;
import org.anapedra.schoolaertesaber.dtos.PhoneGetDTO;
import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.entities.Phone;
import org.anapedra.schoolaertesaber.entities.Registration;
import org.anapedra.schoolaertesaber.entities.enums.PhoneType;
import org.anapedra.schoolaertesaber.factories.PhoneFactory;
import org.anapedra.schoolaertesaber.factories.RegistrationFactory;
import org.anapedra.schoolaertesaber.reposirories.PhoneRepository;
import org.anapedra.schoolaertesaber.services.exceptions.ResourceNotFoundException;
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
import java.util.ArrayList;
import java.util.List;
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




    @Test
    public void findByIdShouldReturnRegistrationWhenExistingId() {
        long existId = 1L;


        Mockito.when(repository.findById(existId)).thenReturn(Optional.of(PhoneFactory.createPhone()));
        PhoneGetDTO result = service.findById(existId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(),existId);

        Mockito.verify(repository, times(1)).findById(existId);


    }

    @Test
    public void findBYIdThrowResourceNotFindExceptionWhenDoesNotExistingId() throws Exception{
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
        PageImpl<Phone> page= new PageImpl<>(List.of(PhoneFactory.createPhone()));

        Mockito.when(repository.findAllPhone(any(), any(),any(),any(),any(),(Pageable)ArgumentMatchers.any())).thenReturn(page);

       Page<PhoneGetDTO> result = service.findAllPaged(firstName,lastName, profession, min.toString(),  max.toString(), pageable);

      Assertions.assertNotNull(result);
       assertEquals(result.getSize(), 1);

    }









    @Test
    public void findAllPagedShouldReturnPage() {

        PageImpl<Phone> page = new PageImpl<>(List.of(PhoneFactory.createPhone()));
        Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 12);

        Page<PhoneGetDTO> result = service.findAllPaged(pageable);

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
    public void deleteShouldNoThingWhenExistingId() {
        Long existId = 1L;
        Mockito.when(repository.existsById(existId)).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(existId);


        Assertions.assertDoesNotThrow(() -> {
            service.delete(existId);
        });

        Mockito.verify(repository, times(1)).deleteById(existId);
    }

    @Test
    public void updateShouldReturnRegistrationDTOWhenIdExists() {
        Long existingId = 1L;
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(new Phone());

        when(repository.save(ArgumentMatchers.any())).thenReturn(PhoneFactory.createPhone());

        service.update(existingId,PhoneFactory.createPhoneDTO());

        Optional<Phone> result = repository.findById(PhoneFactory.createPhone().getId());

        Assertions.assertNotNull(result);
        assertEquals("35264789", PhoneFactory.createPhoneDTO().getNumber());
        assertEquals(PhoneType.WORK_PHONE, PhoneFactory.createPhoneDTO().getPhoneType());



        Mockito.verify(repository, times(1)).findById(PhoneFactory.createPhone().getId());

    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {
        long nonExistingId = 2000000L;
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        PhoneDTO dto= PhoneFactory.createPhoneDTO();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, dto);
        });
    }







}
