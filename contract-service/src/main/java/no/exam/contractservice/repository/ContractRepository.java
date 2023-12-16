package no.exam.contractservice.repository;

import no.exam.contractservice.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Contract findContractById(Long contractId);
}
