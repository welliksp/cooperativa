package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.service.IKafkaConsumer;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class KafkaConsumer implements IKafkaConsumer {

    @Override
    @KafkaListener(topics = "resultado-votacao", groupId = "cooperativa-group")
    public void consume(String message) {
        log.info("Mensagem recebida: {}", message);
    }
}
