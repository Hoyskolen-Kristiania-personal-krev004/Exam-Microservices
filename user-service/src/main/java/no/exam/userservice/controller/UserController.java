package no.exam.userservice.controller;

import lombok.AllArgsConstructor;
import no.exam.userservice.dto.UserDto;
import no.exam.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping("usr/{name}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable("name") String username){
        UserDto userDto = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PutMapping("{id}/outgoing-payment")
    public ResponseEntity<UserDto> outgoingPayment(@PathVariable("id") Long userId, int value){
        UserDto userDto = userService.outgoingPayment(userId, value);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @PutMapping("{id}/incoming-payment")
    public ResponseEntity<UserDto> incomingPayment(@PathVariable("id") Long userId, int value){
        UserDto userDto = userService.incomingPayment(userId, value);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
