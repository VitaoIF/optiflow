package com.optiflow.dto.response;

import com.optiflow.entities.enums.UserRole;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record UserResponse(UUID id, String name, String email, UserRole userRole, LocalDate createdAt) {
}
