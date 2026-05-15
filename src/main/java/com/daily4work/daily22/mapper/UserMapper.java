package com.daily4work.daily22.mapper;

import com.daily4work.daily22.dto.response.UserResponse;
import com.daily4work.daily22.entity.User;

import java.util.stream.Collectors;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setStatus(user.getStatus());

        if (user.getCompany() != null) {
            response.setCompanyId(user.getCompany().getId());
        }

        if (user.getRoles() != null) {
            response.setRoles(
                    user.getRoles().stream()
                            .map(role -> role.getName())
                            .collect(Collectors.toSet())
            );
        }

        return response;
    }
}
