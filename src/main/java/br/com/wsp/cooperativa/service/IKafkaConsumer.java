package br.com.wsp.cooperativa.service;

public interface IKafkaConsumer {

    void consume(String message);
}
