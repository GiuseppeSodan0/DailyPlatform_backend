package com.daily4work.daily22.service;

import com.daily4work.daily22.dto.request.LoginRequest;
import com.daily4work.daily22.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(LoginRequest request);
}
