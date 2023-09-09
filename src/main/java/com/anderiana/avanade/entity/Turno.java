package com.anderiana.avanade.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "batalha_id")
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

}
