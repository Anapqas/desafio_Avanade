package com.anderiana.avanade.controller;

import com.anderiana.avanade.dto.request.IniciativaDto;
import com.anderiana.avanade.service.HistoricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iniciativa")
@RequiredArgsConstructor
public class IniciativaController {
    private final HistoricoService historicoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@RequestBody IniciativaDto body) {
        return historicoService.iniciarBatalha(body);
    }
}
