package com.anderiana.avanade.service;

import com.anderiana.avanade.dto.PersonagemDto;
import com.anderiana.avanade.entity.Personagem;
import com.anderiana.avanade.repository.PersonagemRepository;
import com.anderiana.avanade.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    public ResponseEntity<Void> create(PersonagemDto request) {
        Personagem personagem = new Personagem(request);
        personagem = this.personagemRepository.save(personagem);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(personagem.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    public ResponseEntity<PersonagemDto> getOne(Long id) {
        Optional<Personagem> personagemOp = this.personagemRepository.findById(id);
        return ResponseEntity.ok().body(personagemOp.orElseThrow(() -> new ObjectNotFoundException("Personagem não encontrado")).toDto());
    }

    public ResponseEntity<List<PersonagemDto> >getAll() {
        return ResponseEntity.ok().body(this.personagemRepository.findAll().stream().map(Personagem::toDto).toList());

    }
    public ResponseEntity<PersonagemDto> edit(PersonagemDto dto, Long id) {
        Personagem personagem = new Personagem(dto, id);
        personagem = this.personagemRepository.save(personagem);
        return ResponseEntity.ok().body(personagem.toDto());
    }

    public ResponseEntity<Void> delete(Long id) {
        this.personagemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
