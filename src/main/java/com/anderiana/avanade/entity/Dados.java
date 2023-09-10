package com.anderiana.avanade.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Setter
@Getter
@NoArgsConstructor
public class Dados {
    private Integer numeroFaces;
    private Integer quantidadeDados;
    private Random  random = new Random();

    public Dados(Integer numeroFaces, Integer quantidadeDados) {
        this.numeroFaces = numeroFaces;
        this.quantidadeDados = quantidadeDados;
    }

    public Integer jogarDados(){
        Integer resultado = 0;
        for (int i = 0; i < this.quantidadeDados; i++) {
            Integer lancamento = random.nextInt(this.numeroFaces);
            lancamento += 1;
            resultado += lancamento;
        }
        return resultado;
    }
}
