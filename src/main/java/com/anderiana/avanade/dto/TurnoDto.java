package com.anderiana.avanade.dto;

public record TurnoDto(Long id
                      ,Integer ordem
                      ,String atacante
                      ,String defensor
                      ,Integer ataque
                      ,Integer defesa
                      ,Integer dano
                      ,Integer saldoVidaAtacante
                      ,Integer saldoVidaDefensor) {
}
