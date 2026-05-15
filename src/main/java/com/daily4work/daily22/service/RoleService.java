package com.daily4work.daily22.service;

import com.daily4work.daily22.dto.request.CreateRoleRequest;
import com.daily4work.daily22.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse create(CreateRoleRequest request);

    RoleResponse getById(Integer id);

    List<RoleResponse> getAll();
}
