package no.exam.transactionservice.controller;

import lombok.AllArgsConstructor;
import no.exam.transactionservice.dto.Transaction;
import no.exam.transactionservice.dto.TransactionEvent;
import no.exam.transactionservice.publisher.TransactionProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private TransactionProducer transactionProducer;

    @PostMapping("transaction")
    public String createTransaction(@RequestBody Transaction transaction){
        TransactionEvent event = new TransactionEvent();
        event.setStatus("PENDING");
        event.setMessage("Transaction pending");
        event.setTransaction(transaction);

        transactionProducer.sendMessage(event);

        return "Transaction sent to the queue...";
    }
}
