package com.group18.repository;

import com.group18.entity.User;
import com.group18.entity.VerificationToken;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VerificationTokenRepositoryTest {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        verificationTokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findByTokenNotNull() {
        User user=new User();
        user.setUsername("user");

        userRepository.save(user);

        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken("token");
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        VerificationToken verificationTokenRepositoryByToken=verificationTokenRepository.findByToken("token");
        Assertions.assertThat(verificationTokenRepositoryByToken).isNotNull();
    }

    @Test
    void findByTokenTrue() {
        User user=new User();
        user.setUsername("user");

        userRepository.save(user);

        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken("token");
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        VerificationToken verificationTokenRepositoryByToken=verificationTokenRepository.findByToken("token");
        assertTrue(verificationToken.getToken().equals("token"));
    }

    @Test
    void findByUserNotNull() {
        User user=new User();
        user.setUsername("user");

        userRepository.save(user);

        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken("token");
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        VerificationToken verificationTokenRepositoryByToken=verificationTokenRepository.findByUser(user);
        Assertions.assertThat(verificationTokenRepositoryByToken).isNotNull();
    }

    @Test
    void findByUserTrue() {
        User user=new User();
        user.setUsername("user");

        userRepository.save(user);

        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken("token");
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);

        VerificationToken verificationTokenRepositoryByToken=verificationTokenRepository.findByUser(user);
        assertTrue(verificationToken.getToken().equals("token"));
    }

}