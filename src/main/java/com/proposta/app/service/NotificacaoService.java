package com.proposta.app.service;

import com.proposta.app.dto.PropostaResponseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDTO propostaResponseDTO, String exchange){
        rabbitTemplate.convertAndSend(exchange,"", propostaResponseDTO);
    }
}
