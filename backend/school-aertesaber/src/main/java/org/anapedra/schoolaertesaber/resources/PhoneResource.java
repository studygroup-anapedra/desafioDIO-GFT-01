package org.anapedra.schoolaertesaber.resources;

import jakarta.validation.Valid;
import org.anapedra.schoolaertesaber.dtos.PhoneDTO;
import org.anapedra.schoolaertesaber.services.PhoneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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




    @GetMapping
    public ResponseEntity<Page<PhoneDTO>> findAll(Pageable pageable) {
        Page<PhoneDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<PhoneDTO> findById(@PathVariable("id") Long id) {
        PhoneDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }



    @PostMapping
    public ResponseEntity<PhoneDTO> insert(@RequestBody @Valid PhoneDTO dto) {
        PhoneDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }




    @PutMapping(value = "/{id}")
    public ResponseEntity<PhoneDTO> update(@PathVariable Long id, @RequestBody @Valid PhoneDTO dto) {
        PhoneDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }




}



