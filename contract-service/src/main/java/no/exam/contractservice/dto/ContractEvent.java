package no.exam.contractservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractEvent {
    private String eventStatus;
    private String eventMessage;
    private TransactionContract contract;
}
