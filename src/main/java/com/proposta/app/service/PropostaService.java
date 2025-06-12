package com.proposta.app.service;

import com.proposta.app.dto.PropostaRequestDTO;
import com.proposta.app.dto.PropostaResponseDTO;

import com.proposta.app.entity.Proposta;
import com.proposta.app.mapper.PropostaMapper;
import com.proposta.app.repository.PropostaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PropostaService {


    private PropostaRepository propostaRepository;

    private NotificacaoRabbitService notificacaoRabbitService;

    @Value("${spring.rabbitmq.propostaPendente.exchange}")
    private String exchange;

    public PropostaService(PropostaRepository propostaRepository, NotificacaoRabbitService notificacaoRabbitService, @Value("${spring.rabbitmq.propostaPendente.exchange}") String exchange) {
        this.propostaRepository = propostaRepository;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchange = exchange;
    }



    public PropostaResponseDTO criar(PropostaRequestDTO requestDTO){
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
        propostaRepository.save(proposta);

        notificarRabbitMq(proposta);

        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public void notificarRabbitMq(Proposta proposta){
        try{
            notificacaoRabbitService.notificar(proposta,exchange);
        }catch (RuntimeException ex){
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }


    public List<PropostaResponseDTO> obterProposta(){
        return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());
    }
}
