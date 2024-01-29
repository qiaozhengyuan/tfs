package com.tfs.q1jwa.authentication;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepo extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);
}