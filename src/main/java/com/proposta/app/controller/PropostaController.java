package com.proposta.app.controller;

import com.proposta.app.dto.PropostaRequestDTO;
import com.proposta.app.dto.PropostaResponseDTO;
import com.proposta.app.service.PropostaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {


    private PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO requestDTO){
        PropostaResponseDTO responseDTO = propostaService.criar(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    Public ResponseEntity
}
