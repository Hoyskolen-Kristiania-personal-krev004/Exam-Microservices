package no.exam.userservice.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.exam.userservice.dto.TransactionEvent;
import no.exam.userservice.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionConsumer {

    private UserService userService;

    @RabbitListener(queues = "${rabbitmq.queue.transaction.name}")
    @RabbitHandler
    public void consume(TransactionEvent event){
        log.info("Received transaction from queue");
        log.info(String.format("=> %s", event.toString()));

        Long senderId = event.getTransaction().getSenderId();
        Long receiverId = event.getTransaction().getSenderId();
        int value = event.getTransaction().getValue();

        userService.outgoingPayment(senderId, value);
        userService.incomingPayment(receiverId, value);


        log.info("Transaction completed");
    }
}
