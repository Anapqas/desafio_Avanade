package com.anderiana.avanade.dto;

import java.util.List;

public record BatalhaDto(Long batalhaId
                        ,String heroi
                        ,String monstro
                        ,String quemIniciou
                        ,Boolean isFinalizada
                        ,List<TurnoDto> turnos){
}
