package no.exam.transactionservice.publisher;

import lombok.extern.slf4j.Slf4j;
import no.exam.transactionservice.dto.TransactionEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.binding.routing.key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public TransactionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(TransactionEvent transactionEvent){
        log.info(String.format("Transaction event sent to the queue => %s", transactionEvent.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, transactionEvent);
    }
}
