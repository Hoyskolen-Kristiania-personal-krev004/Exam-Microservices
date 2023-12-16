package no.exam.contractservice.service.impl;

import lombok.AllArgsConstructor;
import no.exam.contractservice.dto.APIResponseDto;
import no.exam.contractservice.dto.ContractDto;
import no.exam.contractservice.dto.RentableDto;
import no.exam.contractservice.dto.UserDto;
import no.exam.contractservice.entity.Contract;
import no.exam.contractservice.mapper.ContractMapper;
import no.exam.contractservice.repository.ContractRepository;
import no.exam.contractservice.service.ContractService;
import no.exam.contractservice.service.RentableAPIClient;
import no.exam.contractservice.service.UserAPIClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {
    private ContractRepository contractRepository;
    private RentableAPIClient rentableAPIClient;
    private UserAPIClient userAPIClient;
    @Override
    public ContractDto createContract(ContractDto contractDto) {
        Contract createdContract = contractRepository.save(ContractMapper.mapToContract(contractDto));
        return ContractMapper.mapToContractDto(createdContract);
    }

    @Override
    public APIResponseDto getContractById(Long id) {

        Contract contract = contractRepository.findContractById(id);
        RentableDto rentable = rentableAPIClient.getRentableById(contract.getRentableId());
        UserDto owner = userAPIClient.getUser(contract.getOwnerId());
        UserDto renter = userAPIClient.getUser(contract.getRenterId());

        APIResponseDto apiResponseDto = new APIResponseDto();

        apiResponseDto.setRentable(rentable);
        apiResponseDto.setOwner(owner);
        apiResponseDto.setRenter(renter);
        apiResponseDto.setContract(ContractMapper.mapToContractDto(contract));

        return apiResponseDto;
    }
}
