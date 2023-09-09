package com.anderiana.avanade.dto;

import com.anderiana.avanade.entity.Personagem;
import com.anderiana.avanade.entity.Turno;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public record BatalhaDto(Long id
                        ,String heroi
                        ,String monstro
                        ,String quemIniciou
                        ,List<Turno> turnos){
}
