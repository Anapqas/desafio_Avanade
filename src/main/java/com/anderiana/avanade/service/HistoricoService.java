package com.anderiana.avanade.service;

import com.anderiana.avanade.dto.BatalhaDto;
import com.anderiana.avanade.dto.PersonagemDto;
import com.anderiana.avanade.entity.Batalha;
import com.anderiana.avanade.entity.Personagem;
import com.anderiana.avanade.repository.BatalhaRepository;
import com.anderiana.avanade.repository.PersonagemRepository;
import com.anderiana.avanade.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoService {

    private final BatalhaRepository batalhaRepository;
    public ResponseEntity<BatalhaDto> getOne(Long id) throws ObjectNotFoundException{ //todo
        Optional<Batalha> batalhaOp = this.batalhaRepository.findById(id);
        return ResponseEntity.ok().body(batalhaOp.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado")).toDto());
    }
    public ResponseEntity<List<BatalhaDto> >getAll() {
        return ResponseEntity.ok().body(this.batalhaRepository.findAll().stream().map(batalha -> batalha.toDto()).toList());
    }


}
