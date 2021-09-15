package com.example.tech1.dto;

import com.example.tech1.model.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String name;
    private Integer age;
    private String login;
    private String password;
    private List<Article> articles;
}
