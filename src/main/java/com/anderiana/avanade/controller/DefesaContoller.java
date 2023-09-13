package com.anderiana.avanade.controller;

import com.anderiana.avanade.dto.request.AtaqueRequestDto;
import com.anderiana.avanade.dto.request.DefesaRequestDto;
import com.anderiana.avanade.dto.response.AtaqueResponseDto;
import com.anderiana.avanade.dto.response.DefesaResponseDto;
import com.anderiana.avanade.service.AtaqueService;
import com.anderiana.avanade.service.DefesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{batalhaId}/defesa/{turnoId}")
@RequiredArgsConstructor
public class DefesaContoller {
    private final DefesaService defesaService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DefesaResponseDto>execute(@RequestBody DefesaRequestDto body, @PathVariable Long turnoId) {
        return defesaService.execute(body, turnoId);
    }
}
