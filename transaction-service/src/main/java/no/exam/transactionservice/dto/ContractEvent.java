package no.exam.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractEvent {
    private String eventStatus;
    private String eventMessage;
    private ContractDto contract;
}
