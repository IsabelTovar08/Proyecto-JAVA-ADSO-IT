package com.sena.crud_basic.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ListMunicipality;
import com.sena.crud_basic.interfaces.IMunicipality;
import com.sena.crud_basic.model.department;
import com.sena.crud_basic.model.municipality;

@Service
public class municipalityService {
    @Autowired
    private IMunicipality municipalityRepository;

    public List<ListMunicipality> getAllMunicipality() {
        try {
            var MunicipalityList = municipalityRepository.findAll();
            return MunicipalityList.stream()
                    .map(this::convertMunicipalityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los detalle reserva: " + e.getMessage(), e);
        }
    }

    public ListMunicipality convertMunicipalityToDto(municipality municipality){
        department departamento = new department();
        departamento.setIdDepartment(municipality.getDepartment().getIdDepartment());
        return new ListMunicipality(
            municipality.getIdMunicipality(), 
            municipality.getNameMunicipality(), 
            municipality.getDepartment().getIdDepartment(),
            municipality.getDepartment().getNameDepartment()
        );
    }
}
