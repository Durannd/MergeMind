package com.ricael.mergemind.dto;

public record PasswordUpdateDTO(
        String currentPassword, // Para validar se é ele mesmo
        String newPassword
) {}
