package com.autoflex.supply_core.global.auth.controller;

import com.autoflex.supply_core.domain.user.dtos.UserCreate;
import com.autoflex.supply_core.domain.user.model.User;
import com.autoflex.supply_core.global.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserCreate request) {
        User entity = request.toEntity();
        authService.createUser(entity);
    }

}
