package com.daily4work.daily22.service.impl;

import com.daily4work.daily22.dto.request.LoginRequest;
import com.daily4work.daily22.dto.response.AuthResponse;
import com.daily4work.daily22.security.CustomUserDetails;
import com.daily4work.daily22.security.JwtTokenProvider;
import com.daily4work.daily22.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority().replace("ROLE_", ""))
                .collect(Collectors.toList());

        String token = jwtTokenProvider.generateToken(
                userDetails.getId(),
                userDetails.getUsername(),
                roles
        );

        return new AuthResponse(
                token,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getUsername(),
                ""
        );
    }
}
