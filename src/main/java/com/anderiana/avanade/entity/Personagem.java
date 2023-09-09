package com.anderiana.avanade.entity;

import com.anderiana.avanade.dto.TipoPersonagem;
import com.anderiana.avanade.dto.PersonagemDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "personagem")
@NoArgsConstructor
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private TipoPersonagem tipoPersonagem;
    private Integer vida;
    private Integer defesa;
    private Integer forca;
    private Integer agilidade;
    private Integer quantidadeDados;
    private Integer facesDado;

   public Personagem(PersonagemDto dto){
        this.nome = dto.nome();
        this.tipoPersonagem = dto.tipoPersonagem();
        this.vida = dto.vida();
        this.defesa = dto.defesa();
        this.forca = dto.forca();
        this.agilidade = dto.agilidade();
        this.quantidadeDados = dto.quantidadeDados();
        this.facesDado = dto.facesDado();
    }
    public Personagem(PersonagemDto dto, Long id){
        this(dto);
        this.id = id;
    }


    public Integer executeDano(){
       //TODO
        return null;
    }

    public PersonagemDto toDto(){
        return new PersonagemDto(this.id
                                ,this.tipoPersonagem
                                ,this.nome
                                ,this.vida
                                ,this.defesa
                                ,this.forca
                                ,this.agilidade
                                ,this.quantidadeDados
                                ,this.facesDado);
    }
}
