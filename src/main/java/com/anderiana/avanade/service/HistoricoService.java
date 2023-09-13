package com.anderiana.avanade.service;

import com.anderiana.avanade.dto.BatalhaDto;
import com.anderiana.avanade.dto.TipoPersonagem;
import com.anderiana.avanade.dto.request.IniciativaDto;
import com.anderiana.avanade.entity.Batalha;
import com.anderiana.avanade.entity.Dados;
import com.anderiana.avanade.entity.Personagem;
import com.anderiana.avanade.entity.Turno;
import com.anderiana.avanade.repository.BatalhaRepository;
import com.anderiana.avanade.repository.PersonagemRepository;
import com.anderiana.avanade.repository.TurnoRepository;
import com.anderiana.avanade.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricoService {

    private final BatalhaRepository batalhaRepository;
    private final PersonagemRepository personagemRepository;
    private final TurnoRepository turnoRepository;
    public ResponseEntity<Void> iniciarBatalha (IniciativaDto request) {
        Optional<Personagem> heroi = personagemRepository.findById(request.heroi());
        Optional<Personagem> monstro = personagemRepository.findById(request.monstro());
        if (heroi.isPresent() && monstro.isPresent()){
            TipoPersonagem quemComeca = decideQuemComeca();
            Personagem atacante = (quemComeca == TipoPersonagem.HEROI)? heroi.get():monstro.get();
            Personagem defensor = (quemComeca == TipoPersonagem.HEROI)? monstro.get():heroi.get();
            Batalha batalha = new Batalha(heroi.get(), monstro.get(),atacante);
            Turno primeiroTurno = new Turno(batalha,atacante,defensor);
            batalha.setTurnos(Arrays.asList(primeiroTurno));
            batalha = this.batalhaRepository.save(batalha);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("batalha/{id}").buildAndExpand(batalha.getId()).toUri();
            return ResponseEntity.created(uri).build();
       }else{
            throw new ObjectNotFoundException("Personagem não encontrado.");
        }
    }
    public ResponseEntity<BatalhaDto> getOne(Long id) {
        Optional<Batalha> batalhaOp = this.batalhaRepository.findById(id);
        return ResponseEntity.ok().body(batalhaOp.orElseThrow(() -> new ObjectNotFoundException("Batalha não encontrada")).toDto());
    }
    public ResponseEntity<List<BatalhaDto>>getAll() {
        return ResponseEntity.ok().body(this.batalhaRepository.findAll().stream().map(Batalha::toDto).toList());
    }
    public TipoPersonagem decideQuemComeca (){
        TipoPersonagem quemInicia = null;
        while (quemInicia == null){
            quemInicia = lancaDadosIniciatva();
        }
        return quemInicia;
    }
    public TipoPersonagem lancaDadosIniciatva(){
        Integer heroi = new Dados(20,1).jogarDados();
        Integer monstro = new Dados(20,1).jogarDados();
        if(heroi>monstro){
            return TipoPersonagem.HEROI;
        } else if (monstro>heroi) {
            return TipoPersonagem.MONSTRO;
        }else{
            return null;
        }
    }
}
