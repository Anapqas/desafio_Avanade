package com.anderiana.avanade.controller;

import com.anderiana.avanade.dto.PersonagemDto;
import com.anderiana.avanade.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagem")
@RequiredArgsConstructor
public class PersonagemController {
    private final PersonagemService personagemService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody PersonagemDto body) {
        return personagemService.create(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemDto> getOne(@PathVariable Long id) {
        return personagemService.getOne(id);
    }
    @GetMapping
    public ResponseEntity<List<PersonagemDto>> getAll(@RequestParam( value = "filter", defaultValue = "") String filter) {
        return personagemService.getAll();
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonagemDto> edit(@RequestBody PersonagemDto body, @PathVariable Long id) {
        return personagemService.edit(body, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  delete(@PathVariable Long id) {
       return personagemService.delete(id);
    }

}
