package com.example.tech1.rest;

import com.example.tech1.dto.ArticleDto;
import com.example.tech1.exception.ValidationException;
import com.example.tech1.services.DefaultArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
@CrossOrigin
public class ArticleController {
    private final DefaultArticleService articleService;
    @PostMapping("/save")
    public ArticleDto saveArticle(@RequestBody ArticleDto articleDto) throws ValidationException {
        return articleService.saveArticle(articleDto);
    }

    @GetMapping(path = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArticleDto> findAllArticle() {
        return articleService.findAll();
    }
}
