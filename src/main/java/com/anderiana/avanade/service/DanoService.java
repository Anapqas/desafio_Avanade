package com.anderiana.avanade.service;

import com.anderiana.avanade.dto.response.DanoResponseDto;
import com.anderiana.avanade.entity.Batalha;
import com.anderiana.avanade.entity.Dados;
import com.anderiana.avanade.entity.Turno;
import com.anderiana.avanade.repository.BatalhaRepository;
import com.anderiana.avanade.repository.PersonagemRepository;
import com.anderiana.avanade.repository.TurnoRepository;
import com.anderiana.avanade.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DanoService {
    private final PersonagemRepository personagemRepository;
    private final TurnoRepository turnoRepository;
    private final BatalhaRepository batalhaRepository;
    public ResponseEntity<DanoResponseDto> execute(Long turnoId){
        Optional<Turno> turnop = this.turnoRepository.findById(turnoId);
        Turno turnoAtual = turnop.orElseThrow(()->new ObjectNotFoundException("Turno não encontrado."));
        Integer dano = 0;
        if (turnoAtual.getDefesa()<turnoAtual.getAtaque()){
            dano = new Dados(turnoAtual.getAtacante().getFacesDado(), turnoAtual.getAtacante().getQuantidadeDados()).jogarDados() + turnoAtual.getAtacante().getForca();
            turnoAtual.setDano(dano);
            turnoAtual.setSaldoVidaDefensor(turnoAtual.getSaldoVidaDefensor() - dano);
            this.turnoRepository.save(turnoAtual);
        } else {
            turnoAtual.setDano(0);
            this.turnoRepository.save(turnoAtual);
        }
        if (turnoAtual.getSaldoVidaDefensor()<0){
            Optional<Batalha> batalha = this.batalhaRepository.findById(turnoAtual.getBatalha().getId());
            batalha.orElseThrow(()->new ObjectNotFoundException("Batalha não encontrada.")).setIsFinalizada(Boolean.TRUE);
            this.batalhaRepository.save(batalha.get());
        }else{
           Turno turnoProximo = new Turno(turnoAtual.getBatalha(), turnoAtual.getDefensor(), turnoAtual.getAtacante());
           turnoProximo.setOrdem(turnoAtual.getOrdem()+1);
           turnoProximo.setSaldoVidaDefensor(turnoAtual.getSaldoVidaAtacante());
           turnoProximo.setSaldoVidaAtacante(turnoAtual.getSaldoVidaDefensor());
           this.turnoRepository.save(turnoProximo);
        }
        return ResponseEntity.ok().body(new DanoResponseDto(dano));
    }
}
