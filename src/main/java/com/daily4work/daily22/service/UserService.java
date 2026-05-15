package com.daily4work.daily22.service;

import com.daily4work.daily22.dto.request.CreateUserRequest;
import com.daily4work.daily22.dto.request.UpdateUserRequest;
import com.daily4work.daily22.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse getById(Integer id);

    List<UserResponse> getAll();

    UserResponse update(Integer id, UpdateUserRequest request);

    void delete(Integer id);
}
