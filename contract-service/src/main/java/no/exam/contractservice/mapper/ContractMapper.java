package no.exam.contractservice.mapper;

import no.exam.contractservice.dto.ContractDto;
import no.exam.contractservice.entity.Contract;

public class ContractMapper {
    public static ContractDto mapToContractDto(Contract contract){
        return new ContractDto(contract.getId(), contract.getCreationDate(), contract.getStartDate(), contract.getEndDate(), contract.getOwnerId(), contract.getRenterId(), contract.getRentableId(), contract.getRentalCost());
    }

    public static Contract mapToContract(ContractDto contractDto){
        return new Contract(contractDto.getId(), contractDto.getCreationDate(), contractDto.getStartDate(), contractDto.getEndDate(), contractDto.getOwnerId(), contractDto.getRenterId(), contractDto.getRentableId(), contractDto.getRentalCost());
    }
}
