package com.proposta.app.service;

import com.proposta.app.dto.PropostaRequestDTO;
import com.proposta.app.dto.PropostaResponseDTO;

import com.proposta.app.entity.Proposta;
import com.proposta.app.mapper.PropostaMapper;
import com.proposta.app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropostaService {

    private PropostaRepository propostaRepository;

    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
        propostaRepository.save(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);

    }
}
