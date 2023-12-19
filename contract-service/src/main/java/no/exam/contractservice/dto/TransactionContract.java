package no.exam.contractservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionContract {
    private Long id;
    private Long ownerId;
    private Long renterId;
    private Long rentableId;
    private int rentalCost;
}
