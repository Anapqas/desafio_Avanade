package com.anderiana.avanade.controller;

import com.anderiana.avanade.dto.request.AtaqueRequestDto;
import com.anderiana.avanade.dto.response.AtaqueResponseDto;
import com.anderiana.avanade.service.AtaqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{batalhaId}/ataque/{turnoId}")
@RequiredArgsConstructor
public class AtaqueContoller {
    private final AtaqueService ataqueService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AtaqueResponseDto>execute(@RequestBody AtaqueRequestDto body, @PathVariable Long turnoId) {
        return ataqueService.execute(body, turnoId);
    }
}
