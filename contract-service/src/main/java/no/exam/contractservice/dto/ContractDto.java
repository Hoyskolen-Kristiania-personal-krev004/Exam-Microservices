package no.exam.contractservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Long id;
    private LocalDateTime creationDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long ownerId;
    private Long renterId;
    private Long rentableId;
    private int rentalCost;
}
