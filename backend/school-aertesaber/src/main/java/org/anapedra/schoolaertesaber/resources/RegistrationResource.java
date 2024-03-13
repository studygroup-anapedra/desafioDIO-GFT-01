package org.anapedra.schoolaertesaber.resources;

import jakarta.validation.Valid;
import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.dtos.RegistrationMinDTO;
import org.anapedra.schoolaertesaber.services.RegistrationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/registrations")
public class RegistrationResource {
    private final RegistrationService service;

    public RegistrationResource(RegistrationService service) {
        this.service = service;
    }


   // @GetMapping
    public ResponseEntity<Page<RegistrationMinDTO>> findAll(
            @RequestParam(value = "firstName",defaultValue = "") String firstName,
            @RequestParam(value = "lastName",defaultValue = "") String lastName,
            @RequestParam(value = "profession",defaultValue = "") String profession,
            @RequestParam(value = "minDate",defaultValue = "") String minDate,
            @RequestParam(value = "maxDate",defaultValue = "") String maxDate,
            Pageable pageable) {
        Page<RegistrationMinDTO> list = service.findAllPaged(firstName.trim(),lastName.trim(),profession.trim(),minDate,maxDate,pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<Page<RegistrationMinDTO>> findAll(Pageable pageable) {
        Page<RegistrationMinDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }



    @GetMapping("/uni/{cpf}")
    public ResponseEntity<RegistrationDTO> findByCpf(@PathVariable String cpf) {
        RegistrationDTO dto = service.findByCpf(cpf);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<RegistrationDTO> findById(@PathVariable Long id) {
        RegistrationDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }



    @PostMapping
    public ResponseEntity<RegistrationDTO> insert(@RequestBody @Valid  RegistrationDTO dto) {
        RegistrationDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
