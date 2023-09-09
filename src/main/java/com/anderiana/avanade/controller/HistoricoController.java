package com.anderiana.avanade.controller;

import com.anderiana.avanade.dto.BatalhaDto;
import com.anderiana.avanade.dto.PersonagemDto;
import com.anderiana.avanade.service.HistoricoService;
import com.anderiana.avanade.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
@RequiredArgsConstructor
public class HistoricoController {
    private final HistoricoService historicoService;
    @GetMapping("/{id}")
   public ResponseEntity<BatalhaDto> getOne(@PathVariable Long id) {
        return historicoService.getOne(id);
    }
    @GetMapping
    public ResponseEntity<List<BatalhaDto>> getAll(@RequestParam( value = "filter", defaultValue = "") String filter) {
        return historicoService.getAll();
    }
}
