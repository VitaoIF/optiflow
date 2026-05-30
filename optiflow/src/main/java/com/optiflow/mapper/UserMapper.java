package com.optiflow.mapper;

import com.optiflow.dto.request.UserRequest;
import com.optiflow.dto.response.UserResponse;
import com.optiflow.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toEntity(UserRequest userRequest){
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .userRole(userRequest.userRole())
                .password(userRequest.password())
                .build();
    }

    public static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .userRole(user.getUserRole())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
