package com.anderiana.avanade.service;

import com.anderiana.avanade.dto.request.DefesaRequestDto;
import com.anderiana.avanade.dto.response.DefesaResponseDto;
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
public class DefesaService {
    private final PersonagemRepository personagemRepository;
    private final TurnoRepository turnoRepository;
    public ResponseEntity<DefesaResponseDto> execute(DefesaRequestDto request, Long turnoId){
        Optional<Personagem> personagemOp = this.personagemRepository.findById(request.defensorId());
        if (personagemOp.isPresent()){
            Integer defesa = personagemOp.get().getDefesa();
            Integer agilidade = personagemOp.get().getAgilidade();
            Integer lancaDado = new Dados(12, 1).jogarDados();
            Integer acaoDefesa = defesa + agilidade + lancaDado;
            Optional<Turno> turnop = this.turnoRepository.findById(turnoId);
            Turno turno = turnop.orElseThrow(()->new ObjectNotFoundException("Turno não encontrado."));
            turno.setDefesa(acaoDefesa);
            this.turnoRepository.save(turno);
            return ResponseEntity.ok().body(new DefesaResponseDto(acaoDefesa));
        }else {
            throw new ObjectNotFoundException("Personagem não encontrado.");
        }
    }
}
