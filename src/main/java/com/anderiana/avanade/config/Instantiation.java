package com.anderiana.avanade.config;

import com.anderiana.avanade.dto.TipoPersonagem;
import com.anderiana.avanade.repository.PersonagemRepository;
import com.anderiana.avanade.entity.Personagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private PersonagemRepository personagemRepository;

    @Override
    public void run(String... args) throws Exception {

        personagemRepository.deleteAll();
        Personagem guerreiro = new Personagem("Guerreiro",TipoPersonagem.HEROI,20,5,7,6,1,12);
        Personagem barbaro= new Personagem("Bárbaro",TipoPersonagem.HEROI,21,2,10,5,2,8);
        Personagem cavaleiro= new Personagem("Cavaleiro",TipoPersonagem.HEROI,26,8,6,3,2,6);
        Personagem orc= new Personagem("Orc",TipoPersonagem.MONSTRO,42,1,7,2,3,4);
        Personagem gigante= new Personagem("Gigante",TipoPersonagem.MONSTRO,34,4,10,4,2,6);
        Personagem lobisomen= new Personagem("Lobisomen",TipoPersonagem.MONSTRO,34,4,7,7,2,4);


        personagemRepository.saveAll(Arrays.asList(guerreiro, barbaro, cavaleiro, orc, gigante, lobisomen));



    }
}
