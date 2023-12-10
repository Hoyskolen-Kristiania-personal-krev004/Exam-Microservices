package no.exam.userservice.service;

import no.exam.userservice.dto.UserDto;

public interface UserService {
    UserDto saveUser(UserDto userDto);
}
