package com.daily4work.daily22.mapper;

import com.daily4work.daily22.dto.response.CompanyResponse;
import com.daily4work.daily22.entity.Company;

public final class CompanyMapper {

    private CompanyMapper() {
    }

    public static CompanyResponse toResponse(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setUid(company.getUid());
        response.setUuid(company.getUuid());
        response.setFactoryName(company.getFactoryName());
        response.setPartitaIva(company.getPartitaIva());
        response.setCodiceFiscale(company.getCodiceFiscale());
        response.setCodiceUnivoco(company.getCodiceUnivoco());
        response.setEmail(company.getEmail());
        response.setPec(company.getPec());
        response.setTelefono(company.getTelefono());
        response.setResponsabile(company.getResponsabile());
        response.setStatus(company.getStatus());
        response.setNote(company.getNote());
        response.setCap(company.getCap());
        response.setComune(company.getComune());
        response.setProvincia(company.getProvincia());
        response.setRegione(company.getRegione());
        response.setNazione(company.getNazione());
        response.setCodiceAteco(company.getCodiceAteco());
        response.setNumDipendenti(company.getNumDipendenti());
        return response;
    }
}
