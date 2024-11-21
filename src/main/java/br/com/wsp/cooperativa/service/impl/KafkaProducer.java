package br.com.wsp.cooperativa.service.impl;

import br.com.wsp.cooperativa.service.IKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer implements IKafkaProducer {

    private static final String TOPIC = "resultado-votacao";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String message) {
        log.info("Enviando mensagem para o Kafka: {}", message);
        kafkaTemplate.send(TOPIC, message);
    }
}
