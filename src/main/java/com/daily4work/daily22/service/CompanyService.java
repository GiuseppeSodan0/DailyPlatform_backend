package com.daily4work.daily22.service;

import com.daily4work.daily22.dto.request.CreateCompanyRequest;
import com.daily4work.daily22.dto.request.UpdateCompanyRequest;
import com.daily4work.daily22.dto.response.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse create(CreateCompanyRequest request);

    CompanyResponse getById(Integer id);

    List<CompanyResponse> getAll();

    CompanyResponse update(Integer id, UpdateCompanyRequest request);

    void delete(Integer id);
}
