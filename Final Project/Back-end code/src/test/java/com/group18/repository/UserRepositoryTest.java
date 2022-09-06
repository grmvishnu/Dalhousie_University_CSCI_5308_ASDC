package com.group18.repository;

import com.group18.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findByUsername() {
        User user=new User();
        long id=1232;
        user.setId(id);
        user.setUsername("abcd");

        userRepository.save(user);

        Optional<User> optionalUser=userRepository.findByUsername("abcd");
        Assertions.assertThat(optionalUser).isNotNull();
    }

    @Test
    void existsByUsername() {
        User user=new User();
        long id=1232;
        user.setId(id);
        user.setUsername("abcd");

        userRepository.save(user);

        boolean existsByUsername=userRepository.existsByUsername("abcd");
        Assertions.assertThat(existsByUsername).isTrue();
    }
}