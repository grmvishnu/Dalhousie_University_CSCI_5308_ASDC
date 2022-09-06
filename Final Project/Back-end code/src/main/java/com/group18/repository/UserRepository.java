package com.group18.repository;

import org.springframework.data.repository.CrudRepository;

import com.group18.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

}
