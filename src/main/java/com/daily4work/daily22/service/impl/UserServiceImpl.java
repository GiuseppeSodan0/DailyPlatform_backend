package com.daily4work.daily22.service.impl;

import com.daily4work.daily22.dto.request.CreateUserRequest;
import com.daily4work.daily22.dto.request.UpdateUserRequest;
import com.daily4work.daily22.dto.response.UserResponse;
import com.daily4work.daily22.entity.Company;
import com.daily4work.daily22.entity.User;
import com.daily4work.daily22.enums.UserStatus;
import com.daily4work.daily22.exception.DuplicateEmailException;
import com.daily4work.daily22.exception.ResourceNotFoundException;
import com.daily4work.daily22.mapper.UserMapper;
import com.daily4work.daily22.repository.CompanyRepository;
import com.daily4work.daily22.repository.UserRepository;
import com.daily4work.daily22.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           CompanyRepository companyRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse create(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(request.getEmail());
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setStatus(UserStatus.ACTIVE);

        if (request.getCompanyId() != null) {
            Company company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company", request.getCompanyId()));
            user.setCompany(company);
        }

        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
        return UserMapper.toResponse(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse update(Integer id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        if (request.getName() != null) {
            user.setName(request.getName());
        }
        if (request.getSurname() != null) {
            user.setSurname(request.getSurname());
        }
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getCompanyId() != null) {
            Company company = companyRepository.findById(request.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company", request.getCompanyId()));
            user.setCompany(company);
        }

        user = userRepository.save(user);
        return UserMapper.toResponse(user);
    }

    @Override
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", id);
        }
        userRepository.deleteById(id);
    }
}
