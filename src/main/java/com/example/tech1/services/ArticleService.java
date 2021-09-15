package com.example.tech1.services;

import com.example.tech1.dto.ArticleDto;

import com.example.tech1.dto.UserDto;
import com.example.tech1.exception.ValidationException;

import java.util.List;

public interface ArticleService {
    ArticleDto saveArticle(ArticleDto articleDto) throws ValidationException;
    List<ArticleDto> findAll();
}
