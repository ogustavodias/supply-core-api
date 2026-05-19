package com.autoflex.supply_core.domain.user.service;

import com.autoflex.supply_core.domain.user.model.User;
import com.autoflex.supply_core.domain.user.repository.UserRepository;
import com.autoflex.supply_core.errors.exceptions.AlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(User user) {
        if (repository.existsByUsername(user.getUsername()))
            throw new AlreadyExistsException("Already exists user with this username");

        var originalPassword = user.getPassword();
        var hashedPassword = passwordEncoder.encode(originalPassword);
        user.setPassword(hashedPassword );
        repository.save(user);
    }

}
