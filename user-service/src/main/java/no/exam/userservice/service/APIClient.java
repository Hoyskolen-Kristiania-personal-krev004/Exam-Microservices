package no.exam.userservice.service;

import no.exam.userservice.dto.RentableDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/*@FeignClient(name = "RENTABLE-SERVICE")
public interface APIClient {
    @GetMapping("api/rentables/owner/{owner-id}")
    List<RentableDto> getAllRentablesByOwner(@PathVariable("owner-id") Long ownerId);
}*/
