package org.anapedra.schoolaertesaber.resources;

import jakarta.validation.Valid;
import org.anapedra.schoolaertesaber.dtos.PhoneDTO;
import org.anapedra.schoolaertesaber.services.PhoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/phones")
public class PhoneResource {
    private final PhoneService service;
    public PhoneResource(PhoneService service) {
        this.service = service;
    }






    @PostMapping
    public ResponseEntity<PhoneDTO> insert(@RequestBody @Valid PhoneDTO dto) {
        PhoneDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }


}



