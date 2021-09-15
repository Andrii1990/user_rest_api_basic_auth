package com.example.tech1.rest;

import com.example.tech1.dto.UserDto;
import com.example.tech1.exception.ValidationException;
import com.example.tech1.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor

@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public UserDto saveUsers(@RequestBody UserDto userDto) throws ValidationException {
        return userService.saveUser(userDto);
    }

    @GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAllUsers() {

        return userService.findAll();
    }

    @GetMapping("/findByLogin")
    public UserDto findByLogin(@RequestParam String login) {

        return userService.findByLogin(login);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<UserDto> search(@RequestParam Map<String, String> allParams) {
        Map.Entry<String, String> actualValue = allParams.entrySet()
                .stream()
                .findFirst()
                .get();
        if (actualValue != null) {
            return userService.search(actualValue.getValue(), actualValue.getKey());
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
