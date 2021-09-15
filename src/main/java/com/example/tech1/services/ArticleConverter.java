package com.example.tech1.services;

import com.example.tech1.dto.ArticleDto;
import com.example.tech1.model.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleConverter {
    public Article fromArticleDtoToArticle(ArticleDto articleDto){
        return  Article
                .builder()
                .id(articleDto.getId())
                .text(articleDto.getText())
                .color(articleDto.getColor())
                .userId(articleDto.getUserId())
                .build();
    }
    public ArticleDto fromArticleToArticleDto(Article article){
        return ArticleDto
                .builder()
                .id(article.getId())
                .color(article.getColor())
                .userId(article.getUserId())
                .text(article.getText())
                .build();
    }
}
