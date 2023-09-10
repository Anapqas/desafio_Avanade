package com.anderiana.avanade.entity;

import com.anderiana.avanade.dto.TurnoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "turno")
@NoArgsConstructor
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @JoinColumn(name = "batalha_id",nullable = false)
    private Batalha batalha;
    private Integer ordem;
    @ManyToOne
    @JoinColumn(name = "atacante_id")
    private Personagem atacante;
    @ManyToOne
    @JoinColumn(name = "defensor_id")
    private Personagem defensor;
    private Integer ataque;
    private Integer defesa;
    private Integer dano;
    private Integer saldoVidaAtacante;
    private Integer saldoVidaDefensor;

    public Turno(Batalha batalha, Personagem atacante, Personagem defensor) {
        this.batalha = batalha;
        this.ordem = 1;
        this.atacante = atacante;
        this.defensor = defensor;
        this.saldoVidaAtacante = atacante.getVida();
        this.saldoVidaDefensor =defensor.getVida();
    }
    public TurnoDto toDto(){
        return new TurnoDto(this.id
                           ,this.ordem
                           ,this.atacante.getNome()
                           ,this.defensor.getNome()
                           ,this.ataque
                           ,this.defesa
                           ,this.dano
                           ,this.saldoVidaAtacante
                           ,this.saldoVidaDefensor);
    }
}
