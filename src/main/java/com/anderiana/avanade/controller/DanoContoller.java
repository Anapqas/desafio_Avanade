package com.anderiana.avanade.controller;

import com.anderiana.avanade.dto.response.DanoResponseDto;
import com.anderiana.avanade.service.DanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{batalhaId}/dano/{turnoId}")
@RequiredArgsConstructor
public class DanoContoller {
    private final DanoService danoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DanoResponseDto>execute(@PathVariable Long turnoId) {
        return danoService.execute(turnoId);
    }
}
