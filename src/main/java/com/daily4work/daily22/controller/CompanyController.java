package com.daily4work.daily22.controller;

import com.daily4work.daily22.dto.request.CreateCompanyRequest;
import com.daily4work.daily22.dto.request.UpdateCompanyRequest;
import com.daily4work.daily22.dto.response.CompanyResponse;
import com.daily4work.daily22.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@Valid @RequestBody CreateCompanyRequest request) {
        CompanyResponse response = companyService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Integer id) {
        CompanyResponse response = companyService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        List<CompanyResponse> response = companyService.getAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@PathVariable Integer id,
                                                  @Valid @RequestBody UpdateCompanyRequest request) {
        CompanyResponse response = companyService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
