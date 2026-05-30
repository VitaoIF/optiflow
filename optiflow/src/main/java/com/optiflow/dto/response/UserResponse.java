package com.optiflow.dto.response;

import com.optiflow.entities.enums.UserRole;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserResponse(Long id, String name, String email, UserRole userRole, LocalDate createdAt) {
}
