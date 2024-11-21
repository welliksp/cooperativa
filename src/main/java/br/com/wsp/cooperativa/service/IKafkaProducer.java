package br.com.wsp.cooperativa.service;

public interface IKafkaProducer {

    void sendMessage(String message);
}
