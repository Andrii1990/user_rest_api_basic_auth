package com.example.tech1.services;

import com.example.tech1.dto.UserDto;
import com.example.tech1.exception.ValidationException;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto) throws ValidationException;

    void deleteUser(Integer userId);

    UserDto findByLogin(String login);

    List<UserDto> findAll();

    List<UserDto> search(String search , String paramType);


}
