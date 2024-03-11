package org.anapedra.schoolaertesaber.resources;

import org.anapedra.schoolaertesaber.dtos.RegistrationDTO;
import org.anapedra.schoolaertesaber.services.RegistrationService;
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




    @GetMapping("/{cpf}")
    public ResponseEntity<RegistrationDTO> findByCpf(@PathVariable String cpf) {
        RegistrationDTO dto = service.findByCpf(cpf);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping(value = "/uni/{id}")
    public ResponseEntity<RegistrationDTO> findById(@PathVariable Long id) {
        RegistrationDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }



    @PostMapping
    public ResponseEntity<RegistrationDTO> insert(@RequestBody RegistrationDTO dto) {
        RegistrationDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

}
