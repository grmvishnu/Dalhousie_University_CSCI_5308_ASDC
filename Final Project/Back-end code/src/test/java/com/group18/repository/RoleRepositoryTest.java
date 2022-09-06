package com.group18.repository;

import com.group18.entity.Role;
import com.group18.entity.model.EnumRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    void findByNameNotNull() {
        Role role=new Role();
        role.setName(EnumRole.ROLE_HOST);

        roleRepository.save(role);

        Optional<Role> roleOptional= this.roleRepository.findByName(EnumRole.ROLE_HOST);
        Assertions.assertThat(roleOptional).isNotNull();
    }

    @Test
    void findByNameTrue() {
        Role role=new Role();
        role.setName(EnumRole.ROLE_HOST);

        roleRepository.save(role);

        Optional<Role> roleOptional= this.roleRepository.findByName(EnumRole.ROLE_HOST);
        assertTrue(roleOptional.get().getName().equals(EnumRole.ROLE_HOST));
    }

    @Test
    void findByNameFalse() {
        Role role=new Role();
        role.setName(EnumRole.ROLE_HOST);

        roleRepository.save(role);

        Optional<Role> roleOptional= this.roleRepository.findByName(EnumRole.ROLE_HOST);
        assertFalse(roleOptional.get().getName().equals(EnumRole.ROLE_ADMIN));
    }
}