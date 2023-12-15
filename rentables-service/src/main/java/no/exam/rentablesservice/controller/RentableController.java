package no.exam.rentablesservice.controller;

import lombok.AllArgsConstructor;
import no.exam.rentablesservice.dto.APIResponseDto;
import no.exam.rentablesservice.dto.RentableDto;
import no.exam.rentablesservice.service.RentableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rentables")
@AllArgsConstructor
public class RentableController {
    private RentableService rentableService;
    @PostMapping
    public ResponseEntity<RentableDto> createRentable(@RequestBody RentableDto rentableDto){
        RentableDto createdRentable = rentableService.createRentable(rentableDto);
        return new ResponseEntity<>(createdRentable, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getRentableById(@PathVariable("id") Long rentableId){
        APIResponseDto apiResponseDto = rentableService.getRentableById(rentableId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
