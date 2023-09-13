package com.anderiana.avanade.service;

import com.anderiana.avanade.dto.request.AtaqueRequestDto;
import com.anderiana.avanade.dto.response.AtaqueResponseDto;
import com.anderiana.avanade.entity.Dados;
import com.anderiana.avanade.entity.Personagem;
import com.anderiana.avanade.entity.Turno;
import com.anderiana.avanade.repository.PersonagemRepository;
import com.anderiana.avanade.repository.TurnoRepository;
import com.anderiana.avanade.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtaqueService {
    private final PersonagemRepository personagemRepository;
    private final TurnoRepository turnoRepository;
    public ResponseEntity<AtaqueResponseDto> execute(AtaqueRequestDto request, Long turnoId){
        Optional<Personagem> personagemOp = this.personagemRepository.findById(request.atacanteId());
        if (personagemOp.isPresent()){
            Integer forca = personagemOp.get().getForca();
            Integer agilidade = personagemOp.get().getAgilidade();
            Integer lancaDado = new Dados(12, 1).jogarDados();
            Integer acaoAtaque = forca + agilidade + lancaDado;
            Optional<Turno> turnop = this.turnoRepository.findById(turnoId);
            Turno turno = turnop.orElseThrow(()->new ObjectNotFoundException("Turno não encontrado."));
            turno.setAtaque(acaoAtaque);
            this.turnoRepository.save(turno);
            return ResponseEntity.ok().body(new AtaqueResponseDto(acaoAtaque));
        }else {
            throw new ObjectNotFoundException("Atacante não encontrado.");
        }
    }
}
