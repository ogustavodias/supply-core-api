package com.autoflex.supply_core.domain.user.dtos;

import com.autoflex.supply_core.domain.user.enums.Role;
import com.autoflex.supply_core.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreate {

    @NotBlank(message = "username is required.")
    private String username;

    @NotBlank(message = "password is required.")
    private String password;

    @NotNull(message = "role is required.")
    private Role role;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
    }

}
