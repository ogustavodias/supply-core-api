package com.autoflex.supply_core.domain.user.controller;

import com.autoflex.supply_core.domain.user.model.User;
import com.autoflex.supply_core.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public void register(User request) {
        userRepository.save(request);
    }

}
