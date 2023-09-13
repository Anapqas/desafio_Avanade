package com.anderiana.avanade.services;

import com.anderiana.avanade.dto.PersonagemDto;
import com.anderiana.avanade.entity.Personagem;
import com.anderiana.avanade.repository.PersonagemRepository;
import com.anderiana.avanade.service.PersonagemService;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PersonagemServiceTest {
    @InjectMocks
    private PersonagemService personagemService;

    @Mock
    private PersonagemRepository personagemRepository;

    @Test
    void shouldInsertPersonagemWithNoErrors(){
        PersonagemDto dto = Mockito.mock(PersonagemDto.class);
        Mockito.when(dto.nome()).thenReturn("teste");
        when(personagemRepository.save(any(Personagem.class))).thenReturn(new Personagem());

        personagemService.create(dto);

        ArgumentCaptor<Personagem> captor = ArgumentCaptor.forClass(Personagem.class);
        Mockito.verify(personagemRepository,Mockito.times(1))
                .save(captor.capture());
        Personagem personagemCreated = captor.getValue();
        Assertions.assertEquals("teste",personagemCreated.getNome());

    }
    @Test
    void shouldGetALlPersonagem() {
        PersonagemDto dto = Mockito.mock(PersonagemDto.class);
        Mockito.when(dto.nome()).thenReturn("teste");
        when(personagemRepository.save(any(Personagem.class))).thenReturn(new Personagem());

        personagemService.create(dto);

        ArgumentCaptor<Personagem> captor = ArgumentCaptor.forClass(Personagem.class);
        Mockito.verify(personagemRepository,Mockito.times(1))
                .save(captor.capture());
        Personagem personagemCreated = captor.getValue();

        Mockito.when(personagemRepository.findAll())
                .thenReturn(List.of(personagemCreated));

        Assertions.assertEquals(1, personagemService.getAll().getBody().size());
    }
}
