package no.exam.contractservice.controller;

import lombok.AllArgsConstructor;
import no.exam.contractservice.dto.ContractDto;
import no.exam.contractservice.service.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/contracts")
@AllArgsConstructor
public class ContractController {

    private ContractService contractService;

    @PostMapping
    public ResponseEntity<ContractDto> createContract(@RequestBody ContractDto contractDto){
        ContractDto createdContract = contractService.createContract(contractDto);
        return new ResponseEntity<>(createdContract, HttpStatus.CREATED);
    }
}
