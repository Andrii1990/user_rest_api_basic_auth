package com.example.tech1.dto;

import com.example.tech1.enums.Color;
import com.example.tech1.model.User;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Integer id;

   private Integer userId;

    private String text;

    private Color color;
}
