package com.anderiana.avanade.dto.request;

import com.anderiana.avanade.dto.TipoPersonagem;

public record PersonagemDto(Long id
                           ,TipoPersonagem tipoPersonagem
                           ,String nome
                           ,Integer vida
                           ,Integer defesa
                           ,Integer forca
                           ,Integer agilidade
                           ,Integer quantidadeDados
                           ,Integer facesDado) {
}
