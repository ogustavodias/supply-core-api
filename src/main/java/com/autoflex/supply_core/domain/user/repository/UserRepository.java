package com.autoflex.supply_core.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoflex.supply_core.domain.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByUsername(String username);

}
