package com.anderiana.avanade.entity;

import com.anderiana.avanade.dto.BatalhaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "batalha")
@NoArgsConstructor
public class Batalha {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "heroi_id")
    private Personagem heroi;
    @ManyToOne
    @JoinColumn(name = "monstro_id")
    private Personagem monstro;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quemIniciou_id")
    private Personagem quemIniciou;
    @OneToMany(mappedBy = "batalha", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Turno> turnos = new ArrayList<>();
    Boolean isFinalizada = Boolean.FALSE;

    public Batalha(Personagem heroi, Personagem monstro, Personagem quemIniciou) {
        this.heroi = heroi;
        this.monstro = monstro;
        this.quemIniciou = quemIniciou;
    }

    public BatalhaDto toDto(){
        return new BatalhaDto(this.id
                            ,this.heroi.getNome()
                            ,this.monstro.getNome()
                            ,this.quemIniciou.getNome()
                            ,this.isFinalizada
                            ,this.turnos.stream().map(Turno::toDto).toList());
    }

}
