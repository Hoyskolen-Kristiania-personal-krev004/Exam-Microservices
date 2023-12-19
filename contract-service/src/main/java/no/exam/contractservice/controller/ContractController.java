package no.exam.contractservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.exam.contractservice.dto.APIResponseDto;
import no.exam.contractservice.dto.ContractDto;
import no.exam.contractservice.dto.ContractEvent;
import no.exam.contractservice.dto.TransactionContract;
import no.exam.contractservice.publisher.ContractProducer;
import no.exam.contractservice.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/contracts")
@AllArgsConstructor
public class ContractController {

    private ContractService contractService;
    private ContractProducer contractProducer;

    @PostMapping
    public ResponseEntity<ContractDto> createContract(@RequestBody ContractDto contractDto){
        ContractDto createdContract = contractService.createContract(contractDto);

        TransactionContract contract = new TransactionContract();
        contract.setId(createdContract.getId());
        contract.setRentableId(createdContract.getRentableId());
        contract.setOwnerId(createdContract.getOwnerId());
        contract.setRenterId(createdContract.getRenterId());
        contract.setRentalCost(createdContract.getRentalCost());

        ContractEvent event = new ContractEvent();
        event.setEventStatus("PENDING");
        event.setEventMessage("Transaction pending");
        event.setContract(contract);

        contractProducer.sendMessage(event);
        log.info("Contract sent to the queue...");
        return new ResponseEntity<>(createdContract, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getContractById(@PathVariable("id") Long id){
        APIResponseDto apiResponseDto = contractService.getContractById(id);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
