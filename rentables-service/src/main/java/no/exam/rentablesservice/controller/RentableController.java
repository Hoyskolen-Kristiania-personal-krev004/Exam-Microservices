package no.exam.rentablesservice.controller;

import lombok.AllArgsConstructor;
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
    public ResponseEntity<RentableDto> saveRentable(@RequestBody RentableDto rentableDto){
        RentableDto savedRentable = rentableService.saveRentable(rentableDto);
        return new ResponseEntity<>(savedRentable, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<RentableDto> getRentableById(@PathVariable("id") Long rentableId){
        RentableDto rentableDto = rentableService.getRentableById(rentableId);
        return new ResponseEntity<>(rentableDto, HttpStatus.OK);
    }
}