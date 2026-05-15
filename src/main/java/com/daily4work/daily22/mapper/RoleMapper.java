package com.daily4work.daily22.mapper;

import com.daily4work.daily22.dto.response.RoleResponse;
import com.daily4work.daily22.entity.Role;

public final class RoleMapper {

    private RoleMapper() {
    }

    public static RoleResponse toResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        return response;
    }
}
