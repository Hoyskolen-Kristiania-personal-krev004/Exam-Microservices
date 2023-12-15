package no.exam.rentablesservice.service;

import no.exam.rentablesservice.dto.APIResponseDto;
import no.exam.rentablesservice.dto.RentableDto;

public interface RentableService {
    RentableDto createRentable(RentableDto rentableDto);

    APIResponseDto getRentableById(Long rentableId);

    /*RentableDto getRentableByOwner(Long ownerId);*/
}
