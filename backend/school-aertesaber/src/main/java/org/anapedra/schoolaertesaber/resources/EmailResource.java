package org.anapedra.schoolaertesaber.resources;

import jakarta.validation.Valid;
import org.anapedra.schoolaertesaber.dtos.EmailDTO;
import org.anapedra.schoolaertesaber.dtos.EmailGetDTO;
import org.anapedra.schoolaertesaber.services.EmailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/emails")
public class EmailResource {

    private final EmailService service;
    public EmailResource(EmailService service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<Page<EmailGetDTO>> findAll(Pageable pageable) {
        Page<EmailGetDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<EmailGetDTO> findById(@PathVariable("id") Long id) {
        EmailGetDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }



    @PostMapping
    public ResponseEntity<EmailDTO> insert(@RequestBody @Valid EmailDTO dto) {
        EmailDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }




    @PutMapping(value = "/{id}")
    public ResponseEntity<EmailDTO> update(@PathVariable Long id, @RequestBody @Valid EmailDTO dto) {
        EmailDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
