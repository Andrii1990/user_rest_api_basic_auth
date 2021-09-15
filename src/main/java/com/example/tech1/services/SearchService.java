package com.example.tech1.services;

import com.example.tech1.model.User;
import com.example.tech1.resources.UserRepository;
import com.example.tech1.resources.UserSpecificationsBuilder;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor

public class SearchService {
    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;

    public List<User> findUserFromArticleColor(String search) {

        return jdbcTemplate.query("SELECT * FROM USERS INNER JOIN ARTICLES ON USERS.ID = ARTICLES.USER_ID where COLOR = '" + search + "';", new BeanPropertyRowMapper<>(User.class));

    }

    public List<User> findUserFromArticleArticleCount(String search) {

        return jdbcTemplate.query("SELECT * FROM USERS WHERE id IN ( SELECT user_id FROM ARTICLES GROUP BY USER_ID HAVING COUNT(*) > " + search + ")", new BeanPropertyRowMapper<>(User.class));

    }

    public List<User> findUserFromUsers(String search) {

        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<User> spec = builder.build();

        return userRepository.findAll(spec);
    }
}
