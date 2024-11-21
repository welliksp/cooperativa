package br.com.wsp.cooperativa.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaProducer kafkaProducer;


    @Test
    void testSendMessage_shouldSendMessageToKafka() {

        String message = "Test message";

        kafkaProducer.sendMessage(message);

        verify(kafkaTemplate, times(1)).send("resultado-votacao", message);
    }

}