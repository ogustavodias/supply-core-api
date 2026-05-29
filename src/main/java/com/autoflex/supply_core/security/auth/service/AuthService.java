package com.autoflex.supply_core.security.auth.service;

import com.autoflex.supply_core.domain.user.model.User;
import com.autoflex.supply_core.domain.user.repository.UserRepository;
import com.autoflex.supply_core.errors.exceptions.AlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new AlreadyExistsException("Already exists user with this username");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
