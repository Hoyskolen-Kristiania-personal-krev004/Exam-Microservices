package no.exam.contractservice.publisher;

import lombok.extern.slf4j.Slf4j;
import no.exam.contractservice.dto.ContractEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContractProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.binding.routing.key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public ContractProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ContractEvent contractEvent){
        log.info(String.format("Transaction event sent to the queue => %s", contractEvent.toString()));
        rabbitTemplate.convertAndSend(exchange, routingKey, contractEvent);
    }
}
