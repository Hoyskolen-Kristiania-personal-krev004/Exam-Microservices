package no.exam.transactionservice.consumer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.exam.transactionservice.dto.ContractEvent;
import no.exam.transactionservice.dto.Transaction;
import no.exam.transactionservice.dto.TransactionEvent;
import no.exam.transactionservice.publisher.TransactionProducer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ContractConsumer {
    private TransactionProducer transactionProducer;

    @RabbitListener(queues = "${rabbitmq.queue.contract.name}")
    @RabbitHandler
    public void consume(ContractEvent event){
        log.info("Received contract from queue");
        log.info(String.format("=> %s", event.toString()));

        Long senderId = event.getContract().getRenterId();
        Long receiverId = event.getContract().getOwnerId();
        int value = event.getContract().getRentalCost();

        Transaction transaction = new Transaction();
        transaction.setReceiverId(receiverId);
        transaction.setSenderId(senderId);
        transaction.setValue(value);

        TransactionEvent transactionEvent = new TransactionEvent();
        transactionEvent.setStatus("PENDING");
        transactionEvent.setMessage("Transaction pending");
        transactionEvent.setTransaction(transaction);

        transactionProducer.sendMessage(transactionEvent);

        log.info("Transaction sent to the queue...");
    }
}
