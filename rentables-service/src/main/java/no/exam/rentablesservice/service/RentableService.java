package no.exam.rentablesservice.service;

import no.exam.rentablesservice.dto.RentableDto;

public interface RentableService {
    RentableDto createRentable(RentableDto rentableDto);

    RentableDto getRentableById(Long rentableId);

    /*RentableDto getRentableByOwner(Long ownerId);*/
}
