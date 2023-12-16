package no.exam.contractservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDto {
    private RentableDto rentable;
    private UserDto owner;
    private UserDto renter;
    private ContractDto contract;
}
