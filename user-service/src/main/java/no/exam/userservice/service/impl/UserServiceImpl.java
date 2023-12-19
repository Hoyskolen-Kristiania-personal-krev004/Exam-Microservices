package no.exam.userservice.service.impl;

import lombok.AllArgsConstructor;
import no.exam.userservice.dto.UserDto;
import no.exam.userservice.entity.User;
import no.exam.userservice.repository.UserRepository;
import no.exam.userservice.service.UserService;
import org.springframework.stereotype.Service;

import static no.exam.userservice.mapper.UserMapper.mapToUser;
import static no.exam.userservice.mapper.UserMapper.mapToUserDto;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(mapToUser(userDto));
        return mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findByUserId(userId);
        return mapToUserDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return mapToUserDto(user);
    }

    @Override
    public UserDto outgoingPayment(Long userId, int value) {
        User user = userRepository.findByUserId(userId);
        user.setCurrentBalance(user.getCurrentBalance()-value);
        return mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto incomingPayment(Long userId, int value) {
        User user = userRepository.findByUserId(userId);
        user.setCurrentBalance(user.getCurrentBalance()+value);
        return mapToUserDto(userRepository.save(user));
    }
}
