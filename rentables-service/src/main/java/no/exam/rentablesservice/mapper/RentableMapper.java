package no.exam.rentablesservice.mapper;

import no.exam.rentablesservice.dto.RentableDto;
import no.exam.rentablesservice.entity.Rentable;

public class RentableMapper {
    //Converts JPA Entity to DTO
    public static RentableDto mapToRentableDto(Rentable rentable){
        return new RentableDto(rentable.getRentableId(), rentable.getRentableName(), rentable.getRentableDescription(), rentable.getOwnerId());
    }
    //Converts DTO to JPA Entity
    public static Rentable mapToRentable(RentableDto rentableDto){
        return new Rentable(rentableDto.getRentableId(), rentableDto.getRentableName(), rentableDto.getRentableDesc(), rentableDto.getOwnerId());
    }
}
