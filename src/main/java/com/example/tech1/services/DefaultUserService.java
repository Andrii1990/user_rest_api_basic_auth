package com.example.tech1.services;

import com.example.tech1.dto.UserDto;
import com.example.tech1.model.User;
import com.example.tech1.exception.ValidationException;
import com.example.tech1.resources.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class DefaultUserService implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final SearchService searchService;


    @Override
    public UserDto saveUser(UserDto UserDto) throws ValidationException {
        validateUserDto(UserDto);
        User savedUser = userRepository.save(userConverter.fromUserDtoToUser(UserDto));
        return userConverter.fromUserToUserDto(savedUser);
    }

    private void validateUserDto(UserDto UserDto) throws ValidationException {
        if (isNull(UserDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(UserDto.getLogin()) || UserDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            return userConverter.fromUserToUserDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> search(String search, String paramType) {
        List<User> findAll;
        switch (paramType) {
            case ("search"):
                findAll = searchService.findUserFromUsers(search);
                break;
            case ("color"):
                findAll = searchService.findUserFromArticleColor(search);
                break;
            case ("articles"):
                findAll = searchService.findUserFromArticleArticleCount(search);
                break;
            default:
                return null;
        }
        if (findAll == null) return null;
        return findAll.stream()
                .map(userConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = userConverter.fromUserDtoToUser(findByLogin(login));
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }
        return new org.springframework.security.core.userdetails.User(u.getLogin(), u.getPassword(), true, true, true, true, new HashSet<>());
    }
}
