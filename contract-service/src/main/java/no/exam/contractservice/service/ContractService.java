package no.exam.contractservice.service;

import no.exam.contractservice.dto.APIResponseDto;
import no.exam.contractservice.dto.ContractDto;

public interface ContractService {
    ContractDto createContract(ContractDto contractDto);

    APIResponseDto getContractById(Long id);
}
