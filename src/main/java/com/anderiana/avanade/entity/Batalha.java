package com.anderiana.avanade.entity;

import com.anderiana.avanade.dto.BatalhaDto;
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
    @JoinColumn(name = "quemIniciou_id")
    private Personagem quemIniciou;
    @OneToMany(mappedBy = "batalha", cascade = CascadeType.PERSIST)
    private List<Turno> turnos = new ArrayList<>();

    public BatalhaDto toDto(){
        return new BatalhaDto(this.id
                            ,this.heroi.getNome()
                            ,this.monstro.getNome()
                            ,this.quemIniciou.getNome()
                            ,this.turnos);
    }

}
