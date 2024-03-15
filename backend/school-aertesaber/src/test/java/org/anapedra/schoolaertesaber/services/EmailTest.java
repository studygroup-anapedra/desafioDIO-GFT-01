package org.anapedra.schoolaertesaber.services;

import org.anapedra.schoolaertesaber.factories.EmailFactory;
import org.anapedra.schoolaertesaber.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmailTest {


    @InjectMocks
    private EmailService service;
    @Mock
    private EmailRepository repository;


    @Test
    public void  insertShouldSaveObjectWhenCorrectDate(){
        when(repository.save(ArgumentMatchers.any())).thenReturn(EmailFactory.createEmail());

        service.insert(EmailFactory.createEmailDTO());

        Optional<Email> result = repository.findById(EmailFactory.createEmail().getId());
        Assertions.assertNotNull(result);
        assertEquals( "ananina@gmail.com", EmailFactory.createEmailDTO().getAddressEmail());
        assertEquals(1l,EmailFactory.createEmailDTO().getId());

        Mockito.verify(repository, times(1)).findById(EmailFactory.createEmailDTO().getId());


    }




    @Test
    public void findByIdShouldReturnResourceWhenExistingId() {
        long existId = 1L;


        Mockito.when(repository.findById(existId)).thenReturn(Optional.of(EmailFactory.createEmail()));
        EmailGetDTO result = service.findById(existId);

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
        PageImpl<Email> page= new PageImpl<>(List.of(EmailFactory.createEmail()));

        Mockito.when(repository.findAllEmail(any(), any(),any(),any(),any(),(Pageable)ArgumentMatchers.any())).thenReturn(page);

       Page<EmailGetDTO>result = service.findAllPaged(firstName,lastName, profession, min.toString(),max.toString(), pageable);
        Mockito.verify(repository, times(1)).findAllPhone(any(), any(),any(),any(),any(),any());


      Assertions.assertNotNull(result);
       assertEquals(result.getSize(), 1);

    }


    @Test
    public void findAllPagedShouldReturnPage() {

        PageImpl<Email> page = new PageImpl<>(List.of(EmailFactory.createEmail()));
        Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);

        Pageable pageable = PageRequest.of(0, 12);

        Page<EmailGetDTO> result = service.findAllPaged(pageable);

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
    public void updateShouldReturnEmailDtoWhenIdExists() {
        Long existingId = 1L;
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(new Phone());

        when(repository.save(ArgumentMatchers.any())).thenReturn(EmailFactory.createEmail());

        service.update(existingId,EmailFactory.createEmailDTO());

        Optional<Email> result = repository.findById(EmailFactory.createEmail().getId());

        Assertions.assertNotNull(result);
        assertEquals( "ananina@gmail.com", EmailFactory.createEmailDTO().getAddressEmail());
        assertEquals(EmailType.WORK_EMAIL, EmailFactory.createEmailDTO().getEmailType());



        Mockito.verify(repository, times(1)).findById(EmailFactory.createEmail().getId());

    }

    @Test
    public void updateShouldReturnResourceNotFoundExceptionWhenIdDoesNotExist() {
        long nonExistingId = 2000000L;
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        EmailDTO dto= EmailFactory.createEmailDTO();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, dto);
        });
    }







}
