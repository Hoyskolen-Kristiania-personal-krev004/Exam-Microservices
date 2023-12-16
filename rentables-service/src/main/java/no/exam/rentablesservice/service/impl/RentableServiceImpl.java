package no.exam.rentablesservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.exam.rentablesservice.dto.APIResponseDto;
import no.exam.rentablesservice.dto.RentableDto;
import no.exam.rentablesservice.dto.UserDto;
import no.exam.rentablesservice.entity.Rentable;
import no.exam.rentablesservice.repository.RentableRepository;
import no.exam.rentablesservice.service.APIClient;
import no.exam.rentablesservice.service.RentableService;
import org.springframework.stereotype.Service;

import static no.exam.rentablesservice.mapper.RentableMapper.mapToRentable;
import static no.exam.rentablesservice.mapper.RentableMapper.mapToRentableDto;

@Slf4j
@Service
@AllArgsConstructor
public class RentableServiceImpl implements RentableService {
    private RentableRepository rentableRepository;
    private APIClient apiClient;
    @Override
    public RentableDto createRentable(RentableDto rentableDto) {
        Rentable createdRentable = rentableRepository.save(mapToRentable(rentableDto));
        return mapToRentableDto(createdRentable);
    }

    @Override
    public RentableDto getRentableById(Long rentableId) {
        return mapToRentableDto(rentableRepository.findByRentableId(rentableId));
    }

    @Override
    @Retry(name = "${spring.application.name}")
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultOwner")
    public APIResponseDto getAPIRentableById(Long rentableId) {

        log.info("Getting Rentable object and owner");

        Rentable rentable = rentableRepository.findByRentableId(rentableId);
        UserDto owner = apiClient.getUser(rentable.getOwnerId());
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setRentable(mapToRentableDto(rentable));
        apiResponseDto.setOwner(owner);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultOwner(Long rentableId, Exception exception) {

        log.info("Failed to get owner, using fallback method");

        Rentable rentable = rentableRepository.findByRentableId(rentableId);
        UserDto owner = new UserDto();
        owner.setUsername("JohnDoe");
        owner.setFirstName("Michael");
        owner.setLastName("McDoesntExist");
        owner.setEmail("John@Doe.404");
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setRentable(mapToRentableDto(rentable));
        apiResponseDto.setOwner(owner);

        return apiResponseDto;
    }
/*    @Override
    public RentableDto getRentableByOwner(Long ownerId) {
        return null;
    }*/
}
