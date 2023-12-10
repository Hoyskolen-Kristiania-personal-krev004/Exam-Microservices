package no.exam.rentablesservice.service.impl;

import lombok.AllArgsConstructor;
import no.exam.rentablesservice.dto.RentableDto;
import no.exam.rentablesservice.entity.Rentable;
import no.exam.rentablesservice.repository.RentableRepository;
import no.exam.rentablesservice.service.RentableService;
import org.springframework.stereotype.Service;

import static no.exam.rentablesservice.mapper.RentableMapper.mapToRentable;
import static no.exam.rentablesservice.mapper.RentableMapper.mapToRentableDto;

@Service
@AllArgsConstructor
public class RentableServiceImpl implements RentableService {
    private RentableRepository rentableRepository;
    @Override
    public RentableDto saveRentable(RentableDto rentableDto) {
        Rentable savedRentable = rentableRepository.save(mapToRentable(rentableDto));
        return mapToRentableDto(savedRentable);
    }

    @Override
    public RentableDto getRentableById(Long rentableId) {
        Rentable rentable = rentableRepository.findByRentableId(rentableId);

        return mapToRentableDto(rentable);
    }

/*    @Override
    public RentableDto getRentableByOwner(Long ownerId) {
        return null;
    }*/
}
