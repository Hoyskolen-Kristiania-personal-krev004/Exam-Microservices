package no.exam.userservice.mapper;

import no.exam.userservice.dto.UserDto;
import no.exam.userservice.entity.User;

public class UserMapper {
    //Converts JPA Entity to DTO
    public static UserDto mapToUserDto(User user){
        return new UserDto(user.getUserId(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getCurrentBalance());
    }
    //Converts DTO to JPA Entity
    public static User mapToUser(UserDto userDto){
        return new User(userDto.getId(), userDto.getUsername(), userDto.getPassword(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getCurrentBalance());
    }
}
