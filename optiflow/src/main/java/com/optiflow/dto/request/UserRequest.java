package com.optiflow.dto.request;

import com.optiflow.entities.enums.UserRole;

public record UserRequest(String name, String email, String password, UserRole userRole) {
}
