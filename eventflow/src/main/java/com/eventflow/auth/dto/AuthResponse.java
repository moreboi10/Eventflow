package com.eventflow.auth.dto;

import com.eventflow.user.dto.UserResponse;

public record AuthResponse(
        String token,
        UserResponse user
) {
}
