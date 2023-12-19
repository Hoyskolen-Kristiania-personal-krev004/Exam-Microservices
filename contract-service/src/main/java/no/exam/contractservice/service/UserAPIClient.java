package no.exam.contractservice.service;

import no.exam.contractservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserAPIClient {
    @GetMapping("api/users/{id}")
    UserDto getUser(@PathVariable("id") Long userId);
}

