package com.proposta.app.agendador;

import com.proposta.app.entity.Proposta;
import com.proposta.app.repository.PropostaRepository;
import com.proposta.app.service.NotificacaoRabbitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {

    public PropostaSemIntegracao(PropostaRepository propostaRepository, @Value("${spring.rabbitmq.propostaPendente.exchange}") String exchange, NotificacaoRabbitService notificacaoRabbitService) {
        this.propostaRepository = propostaRepository;
        this.exchange = exchange;
        this.notificacaoRabbitService = notificacaoRabbitService;
    }

    private PropostaRepository propostaRepository;
    private NotificacaoRabbitService notificacaoRabbitService;
    private String exchange;
    private  final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class)

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostaSemIntegracao() {
        propostaRepository.findAllByIntegradaFalse().forEach(proposta -> {
            try {
                notificacaoRabbitService.notificar(proposta, exchange);
                atualizarProposta(proposta);
            } catch (RuntimeException ex) {
                logger.error(ex.getMessage());
            }
        });
    }

    private void atualizarProposta(Proposta proposta){
        proposta.setIntegrada(true);
        propostaRepository.save(proposta);
    }
}
