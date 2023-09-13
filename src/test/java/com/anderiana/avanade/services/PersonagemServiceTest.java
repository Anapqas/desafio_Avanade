package com.anderiana.avanade.services;

import com.anderiana.avanade.dto.PersonagemDto;
import com.anderiana.avanade.dto.TipoPersonagem;
import com.anderiana.avanade.entity.Personagem;
import com.anderiana.avanade.repository.PersonagemRepository;
import com.anderiana.avanade.service.PersonagemService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

//import org.junit.runner.Runwith;
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
}
