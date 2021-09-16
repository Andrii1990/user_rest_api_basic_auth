package com.example.tech1.prototype;

import com.example.tech1.dto.UserDto;
import com.example.tech1.model.User;

public class UserPrototype {
    public static User aUser(){
        User u = new User();
        u.setName("Bob");
        u.setAge(36);
        u.setLogin("Bob36");
        u.setPassword("secret_pass");
        return u;
    }
    public static UserDto aUserDto(){
        return UserDto.builder()
                .name("Bob")
                .age(36)
                .login("Bob36")
                .password("secret_pass")
                .build();
    }
}
