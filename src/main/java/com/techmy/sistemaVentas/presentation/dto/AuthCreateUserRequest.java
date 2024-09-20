package com.techmy.sistemaVentas.presentation.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
        @NotBlank String password, @NotBlank Integer personaid,
        @Valid AuthCreateRoleRequest roleRequest) {
}