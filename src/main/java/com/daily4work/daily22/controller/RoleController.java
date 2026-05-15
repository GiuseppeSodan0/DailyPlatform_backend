package com.daily4work.daily22.controller;

import com.daily4work.daily22.dto.request.CreateRoleRequest;
import com.daily4work.daily22.dto.response.RoleResponse;
import com.daily4work.daily22.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponse> create(@Valid @RequestBody CreateRoleRequest request) {
        RoleResponse response = roleService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getById(@PathVariable Integer id) {
        RoleResponse response = roleService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAll() {
        List<RoleResponse> response = roleService.getAll();
        return ResponseEntity.ok(response);
    }
}
