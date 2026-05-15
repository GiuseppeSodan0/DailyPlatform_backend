package com.daily4work.daily22.service.impl;

import com.daily4work.daily22.dto.request.CreateRoleRequest;
import com.daily4work.daily22.dto.response.RoleResponse;
import com.daily4work.daily22.entity.Role;
import com.daily4work.daily22.exception.DuplicateRoleException;
import com.daily4work.daily22.exception.ResourceNotFoundException;
import com.daily4work.daily22.mapper.RoleMapper;
import com.daily4work.daily22.repository.RoleRepository;
import com.daily4work.daily22.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponse create(CreateRoleRequest request) {
        if (roleRepository.existsByName(request.getName())) {
            throw new DuplicateRoleException(request.getName());
        }

        Role role = new Role(request.getName());
        role = roleRepository.save(role);
        return RoleMapper.toResponse(role);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse getById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));
        return RoleMapper.toResponse(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::toResponse)
                .toList();
    }
}
