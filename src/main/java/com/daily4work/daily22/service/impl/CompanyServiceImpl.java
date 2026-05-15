package com.daily4work.daily22.service.impl;

import com.daily4work.daily22.dto.request.CreateCompanyRequest;
import com.daily4work.daily22.dto.request.UpdateCompanyRequest;
import com.daily4work.daily22.dto.response.CompanyResponse;
import com.daily4work.daily22.entity.Company;
import com.daily4work.daily22.enums.CompanyStatus;
import com.daily4work.daily22.exception.ResourceNotFoundException;
import com.daily4work.daily22.mapper.CompanyMapper;
import com.daily4work.daily22.repository.CompanyRepository;
import com.daily4work.daily22.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponse create(CreateCompanyRequest request) {
        Company company = new Company();
        applyCreateRequest(company, request);
        company.setStatus(CompanyStatus.ACTIVE);

        company = companyRepository.save(company);
        return CompanyMapper.toResponse(company);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponse getById(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", id));
        return CompanyMapper.toResponse(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponse> getAll() {
        return companyRepository.findAll().stream()
                .map(CompanyMapper::toResponse)
                .toList();
    }

    @Override
    public CompanyResponse update(Integer id, UpdateCompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", id));

        applyUpdateRequest(company, request);

        company = companyRepository.save(company);
        return CompanyMapper.toResponse(company);
    }

    @Override
    public void delete(Integer id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company", id);
        }
        companyRepository.deleteById(id);
    }

    private void applyCreateRequest(Company company, CreateCompanyRequest request) {
        company.setUid(request.getUid());
        company.setUuid(request.getUuid());
        company.setFactoryName(request.getFactoryName());
        company.setPartitaIva(request.getPartitaIva());
        company.setCodiceFiscale(request.getCodiceFiscale());
        company.setCodiceUnivoco(request.getCodiceUnivoco());
        company.setEmail(request.getEmail());
        company.setPec(request.getPec());
        company.setTelefono(request.getTelefono());
        company.setResponsabile(request.getResponsabile());
        company.setNote(request.getNote());
        company.setCap(request.getCap());
        company.setComune(request.getComune());
        company.setProvincia(request.getProvincia());
        company.setRegione(request.getRegione());
        company.setNazione(request.getNazione());
        company.setCodiceAteco(request.getCodiceAteco());
        company.setNumDipendenti(request.getNumDipendenti());
    }

    private void applyUpdateRequest(Company company, UpdateCompanyRequest request) {
        if (request.getUid() != null) company.setUid(request.getUid());
        if (request.getUuid() != null) company.setUuid(request.getUuid());
        if (request.getFactoryName() != null) company.setFactoryName(request.getFactoryName());
        if (request.getPartitaIva() != null) company.setPartitaIva(request.getPartitaIva());
        if (request.getCodiceFiscale() != null) company.setCodiceFiscale(request.getCodiceFiscale());
        if (request.getCodiceUnivoco() != null) company.setCodiceUnivoco(request.getCodiceUnivoco());
        if (request.getEmail() != null) company.setEmail(request.getEmail());
        if (request.getPec() != null) company.setPec(request.getPec());
        if (request.getTelefono() != null) company.setTelefono(request.getTelefono());
        if (request.getResponsabile() != null) company.setResponsabile(request.getResponsabile());
        if (request.getNote() != null) company.setNote(request.getNote());
        if (request.getCap() != null) company.setCap(request.getCap());
        if (request.getComune() != null) company.setComune(request.getComune());
        if (request.getProvincia() != null) company.setProvincia(request.getProvincia());
        if (request.getRegione() != null) company.setRegione(request.getRegione());
        if (request.getNazione() != null) company.setNazione(request.getNazione());
        if (request.getCodiceAteco() != null) company.setCodiceAteco(request.getCodiceAteco());
        if (request.getNumDipendenti() != null) company.setNumDipendenti(request.getNumDipendenti());
    }
}
