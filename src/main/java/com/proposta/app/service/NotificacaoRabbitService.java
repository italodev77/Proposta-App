package com.proposta.app.service;

import com.proposta.app.entity.Proposta;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoRabbitService {

    private RabbitTemplate rabbitTemplate;

    public void notificar(Proposta proposta, String exchange){
        rabbitTemplate.convertAndSend(exchange,"", proposta);
    }
}
