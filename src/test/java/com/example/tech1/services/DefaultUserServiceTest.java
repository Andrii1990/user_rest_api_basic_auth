package com.example.tech1.services;

import com.example.tech1.dto.UserDto;
import com.example.tech1.exception.ValidationException;
import com.example.tech1.resources.UserRepository;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;

import static com.example.tech1.prototype.UserPrototype.aUser;
import static com.example.tech1.prototype.UserPrototype.aUserDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultUserServiceTest {

    private UserRepository userRepository;
    private UserConverter userConverter;
    private SearchService searchService;
    private JdbcTemplate jdbcTemplate;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userConverter = new UserConverter();
        searchService = new SearchService(userRepository, jdbcTemplate);
        jdbcTemplate = new JdbcTemplate();
        userService = new DefaultUserService(userRepository, userConverter, searchService);
    }

    @Test
    void saveUser() throws ValidationException {
        when(userRepository.save(any())).thenReturn(aUser());
        UserDto createdUser = userService.saveUser(aUserDto());
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getName()).isEqualTo(aUserDto().getName());
    }

    @Test
    void saveUserThrowsValidationExceptionWhenLoginIsNull() {
        UserDto userDto = aUserDto();
        userDto.setLogin("");
        assertThrows(ValidationException.class, () -> userService.saveUser(userDto), "Login is empty");
    }

    @Test
    void deleteUser() {
    }

    @Test
    void findByLogin() {
        when(userRepository.findByLogin(eq("Bob36"))).thenReturn(aUser());
        UserDto foundUser  = userService.findByLogin("Bob36");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getLogin()).isEqualTo("Bob36");
    }

    @Test
    void findAll() {
    }

    @Test
    void search() {
    }

    @Test
    void loadUserByUsername() {
    }
}