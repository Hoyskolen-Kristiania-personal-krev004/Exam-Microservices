package no.exam.contractservice.service;

import no.exam.contractservice.dto.RentableDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080", value = "RENTABLE-SERVICE")
public interface RentableAPIClient {
    @GetMapping("api/rentables/contract/{id}")
    RentableDto getRentableById(@PathVariable("id") Long rentableId);
}
