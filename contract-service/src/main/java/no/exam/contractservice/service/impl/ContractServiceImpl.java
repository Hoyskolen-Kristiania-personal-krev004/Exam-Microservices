package no.exam.contractservice.service.impl;

import lombok.AllArgsConstructor;
import no.exam.contractservice.dto.ContractDto;
import no.exam.contractservice.entity.Contract;
import no.exam.contractservice.mapper.ContractMapper;
import no.exam.contractservice.repository.ContractRepository;
import no.exam.contractservice.service.ContractService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {
    private ContractRepository contractRepository;
    @Override
    public ContractDto createContract(ContractDto contractDto) {
        Contract createdContract = contractRepository.save(ContractMapper.mapToContract(contractDto));
        return ContractMapper.mapToContractDto(createdContract);
    }

    @Override
    public ContractDto getContractById(Long id) {
        Contract contract = contractRepository.findContractById(id);
        return ContractMapper.mapToContractDto(contract);
    }
}
