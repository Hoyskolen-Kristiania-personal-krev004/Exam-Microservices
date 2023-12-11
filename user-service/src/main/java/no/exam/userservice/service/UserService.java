package no.exam.userservice.service;

import no.exam.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    UserDto getUserByUsername(String username);
}
