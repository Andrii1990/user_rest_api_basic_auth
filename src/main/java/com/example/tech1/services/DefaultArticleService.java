package com.example.tech1.services;

import com.example.tech1.dto.ArticleDto;
import com.example.tech1.exception.ValidationException;
import com.example.tech1.model.Article;
import com.example.tech1.resources.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
@AllArgsConstructor
public class DefaultArticleService implements ArticleService  {

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;
    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) throws ValidationException {
        validateArticleDto(articleDto);
        Article savedArticle = articleRepository.save(articleConverter.fromArticleDtoToArticle(articleDto));

        return articleConverter.fromArticleToArticleDto(savedArticle);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream().map(articleConverter::fromArticleToArticleDto).collect(Collectors.toList());
    }

    private void validateArticleDto(ArticleDto articleDto) throws ValidationException{
        if(isNull(articleDto)){
            throw new ValidationException("Object article is null");
        };
    }


}
