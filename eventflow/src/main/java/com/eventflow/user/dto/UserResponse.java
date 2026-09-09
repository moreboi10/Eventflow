package com.eventflow.user.dto;

import com.eventflow.user.entity.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        Role role,
        String phone
){
}
