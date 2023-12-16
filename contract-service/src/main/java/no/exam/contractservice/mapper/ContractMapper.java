package no.exam.contractservice.mapper;

import no.exam.contractservice.dto.ContractDto;
import no.exam.contractservice.entity.Contract;

public class ContractMapper {
    public static ContractDto mapToContractDto(Contract contract){
        ContractDto contractDto = new ContractDto(contract.getId(), contract.getCreationDate(), contract.getStartDate(), contract.getEndDate(), contract.getOwnerId(), contract.getRenterId(), contract.getRentableId());
        return contractDto;
    }

    public static Contract mapToContract(ContractDto contractDto){
        Contract contract = new Contract(contractDto.getId(), contractDto.getCreationDate(), contractDto.getStartDate(), contractDto.getEndDate(), contractDto.getOwnerId(), contractDto.getRenterId(), contractDto.getRentableId());
        return contract;
    }
}
