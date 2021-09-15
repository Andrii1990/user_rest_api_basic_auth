package com.example.tech1.services;

import com.example.tech1.dto.UserDto;
import com.example.tech1.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setLogin(userDto.getLogin());
        user.setArticles(userDto.getArticles());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto fromUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .login(user.getLogin())
                .articles(user.getArticles())
                .password(user.getPassword())
                .build();
    }
}
