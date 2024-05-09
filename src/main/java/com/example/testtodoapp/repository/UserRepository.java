package com.example.testtodoapp.repository;

import com.example.testtodoapp.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Long userId);

    Optional<List<User>> findAllByFirstName(String firstName);

    Boolean existsByEmail(String email);
}
