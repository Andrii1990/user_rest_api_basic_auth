package com.example.tech1.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.tech1.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static com.example.tech1.prototype.UserPrototype.aUserDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void saveUsers() throws Exception {
        when(userService.saveUser(any())).thenReturn(aUserDto());
        mockMvc.perform(post("/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(aUserDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(aUserDto())));
    }

    @Test
    void findAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(Collections.singletonList(aUserDto()));
        mockMvc.perform(get("/users/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(aUserDto()))))
                .andExpect(status().isOk());
    }

    @Test
    void findByLogin() {
    }

    @Test
    void search() {
    }

    @Test
    void deleteUsers() {
    }
}