package com.example.tech1.resources;

import com.example.tech1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
    User findByLogin(String login);

}
