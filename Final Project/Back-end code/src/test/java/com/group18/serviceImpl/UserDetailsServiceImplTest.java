package com.group18.serviceImpl;

import com.group18.entity.User;
import com.group18.entity.model.UserDetailsImpl;
import com.group18.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDetailsServiceImplTest {
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userDetailsServiceImpl=new UserDetailsServiceImpl(userRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadUserByUsernameNotNull() {
        User user=new User();
        user.setUsername("abcd");

        Mockito.when(userRepository.findByUsername("abcd")).thenReturn(Optional.of(user));

        UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername("abcd");
        Assertions.assertThat(userDetails).isNotNull();
    }

    @Test
    void loadUserByUsernameTrue() {
        User user=new User();
        user.setUsername("abcd");

        Mockito.when(userRepository.findByUsername("abcd")).thenReturn(Optional.of(user));

        UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername("abcd");
        assertTrue(userDetails.getUsername().equals("abcd"));
    }

    @Test
    void loadUserByUsernameFalse() {
        User user=new User();
        user.setUsername("abcd");

        Mockito.when(userRepository.findByUsername("abcd")).thenReturn(Optional.of(user));

        UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername("abcd");
        assertFalse(userDetails.getUsername().equals("jghw"));
    }
}